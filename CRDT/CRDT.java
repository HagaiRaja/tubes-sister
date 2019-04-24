import java.util.*;

public class CRDT {
    public static final int IDEAL_MAX_INTERVAL = 1000000;
    public static final int IDEAL_MIN_INTERVAL = -1000000;
    List<Data> allData;
    int counter;
    List<Data> deleteBuffer;
    public static List<Integer> userID;
    Data corner;

    public CRDT (int startCounter) {
        allData = new ArrayList<Data>();
        deleteBuffer = new ArrayList<Data>();
        userID = new ArrayList<Integer>();
        counter = startCounter*1000000;

        corner = new Data('\n', 99999, new ArrayList<Integer>());
    }

    public void insertGlobal (Data data) {
        // pengecekan ada tidak di delete buffer
        int index = 0;
        boolean found = false;

        while (!found && index < deleteBuffer.size()) {
            if (deleteBuffer.get(index).getID() == data.getID()) {
                found = true;
            }
            else {
                index++;
            }
        }

        //masukkan aja
        if (!found) {
            this.addData(data);
        }
        else {
            deleteBuffer.remove(index);
        }
    }

    public Data insert (char data, Data left, Data right) {
        // getting global IDs
        int ID = counter;
        counter++;
        
        // getting global position
        List<Integer> position = new ArrayList<Integer>();
        Random r = new Random();
        int result;
        if (left.getPosition().size() == 0) {
            // System.out.println("woi");
            if (allData.size() == 0) { // first entry
                result = r.nextInt(IDEAL_MAX_INTERVAL - IDEAL_MIN_INTERVAL) + IDEAL_MIN_INTERVAL;
                if (result == IDEAL_MIN_INTERVAL) {  // the random range equal to 0
                    result++;
                }
            }
            else {
                int rightValue = allData.get(0).getPosition().get(0);
                result = r.nextInt(rightValue - IDEAL_MIN_INTERVAL) + IDEAL_MIN_INTERVAL;
                if (result == IDEAL_MIN_INTERVAL) {  // the random range equal to 0
                    result++;
                }
            }

            position.add(result);
        }
        else if (right.getPosition().size() == 0) {
            int leftValue = allData.get(allData.size()-1).getPosition().get(0);
            result = r.nextInt(IDEAL_MAX_INTERVAL - leftValue) + leftValue;
            if (result == leftValue) {  // the random range equal to 0
                result++;
            }

            position.add(result);
        }
        else {
            List<Integer> leftPosition = left.getPosition();
            List<Integer> rightPosition =  right.getPosition();
            boolean found = false;
            int index = 0;

            while (!found && index < leftPosition.size() && index < rightPosition.size()) {
                int leftValue = leftPosition.get(index);
                int rightValue = rightPosition.get(index);
                if (leftValue - rightValue > 1) {
                    result = r.nextInt(rightValue-leftValue) + leftValue;
                    if (result == leftValue) {  // the random range equal to 0
                        result++;
                    }
                    position.add(result);
                    found = true;
                }
                else {
                    position.add(leftValue);
                }
                index++;
            }

            if (!found) {
                int leftSize = leftPosition.size();
                int rightSize = rightPosition.size();
                
                if (leftSize > rightSize) {
                    int leftValue = leftPosition.get(index);
                    result = r.nextInt(IDEAL_MAX_INTERVAL - leftValue) + leftValue;
                    if (result == leftValue) {  // the random range equal to 0
                        result++;
                    }
                }
                else if (leftSize < rightSize) {
                    int rightValue = rightPosition.get(index);
                    result = r.nextInt(rightValue - IDEAL_MIN_INTERVAL) + IDEAL_MIN_INTERVAL;
                    if (result == IDEAL_MIN_INTERVAL) {  // the random range equal to 0
                        result++;
                    }
                }
                else {
                    result = r.nextInt(IDEAL_MAX_INTERVAL - IDEAL_MIN_INTERVAL) + IDEAL_MIN_INTERVAL;
                    if (result == IDEAL_MIN_INTERVAL) {  // the random range equal to 0
                        result++;
                    }
                }

                position.add(result);
            }
        }

        // setting new Data
        Data newData = new Data(data, ID, position);
        this.addData(newData);
        return newData;
    }

    public void addData (Data newData) {
        boolean found = false;
        int index = 0;
        // System.out.println("inserting :" + newData.getPosition().toString());
        while (!found && allData.size() > index) {
            int pos = 0;
            boolean foundPos = true;
            int positionSize = allData.get(index).getPosition().size();
            
            while (foundPos && positionSize > pos) {
                // System.out.println("bandingkan index - " + index + ", position" + pos + ":" + allData.get(index).getPosition().get(pos) + " dan " + newData.getPosition().get(pos));
                if (allData.get(index).getPosition().get(pos) > newData.getPosition().get(pos)) {
                    // System.out.println("break");
                    break;
                }
                else if (allData.get(index).getPosition().get(pos) == newData.getPosition().get(pos)) {
                    pos++;
                }
                else {
                    foundPos = false;
                }
            }
            
            if (foundPos) {
                if (positionSize == pos) {
                    index++;
                }
                found = true;
            }
            else {
                index++;
            }
        }
        
        if (!found) {
            allData.add(newData);
        }
        else {
            allData.add(index, newData);
        }

        // for (Data data : allData) {
        //     System.out.println(data.getPosition().toString());
        // }
    }

    public void delete (Data data) {
        int index = 0;
        boolean found = false;

        while (!found && index < allData.size()) {
            if (allData.get(index).getID() == data.getID()) {
                found = true;
            }
            else {
                index++;
            }
        }

        // hapus saja
        if (found) {
            allData.remove(index);
        }
        else {
            deleteBuffer.add(data);
        }
    }

    public void deleteGlobal (Data data) {
        this.delete(data);
    }

    public static int addUser () {
        Random r = new Random();
        int high = 100, low = 1;
        int ID = r.nextInt(high-low) + low; 
        while (userID.contains(ID)) {
            ID = r.nextInt(high-low) + low;
        }
        userID.add(ID);

        return ID;
    }

    public String renderString () {
        String result = "";
        for (Data data : allData) {
            result += data.getAlphabet();
        }

        return result;
    }

    // public static void main(String[] args) {
    //     CRDT test = new CRDT(1);

    //     List<Integer> l1 = new ArrayList<Integer>(); 
    //     l1.add(1);  // adds 1 
    //     l1.add(2);  // adds 2
    //     l1.add(3);  // adds 3
        
    //     Data satu = new Data('a', 1233, l1);

    //     test.addData(satu);

    //     l1.add(2);
    //     satu = new Data('b', 12323, l1);

    //     test.addData(satu);

    //     List<Integer> l2 = new ArrayList<Integer>(); 
    //     l2.add(2);  // adds 2
    //     l2.add(3);  // adds 3

    //     satu = new Data('c', 12323, l2);
    //     test.addData(satu);

    //     l2.add(1, 2);  // adds 3
    //     satu = new Data('d', 12323, l2);
    //     test.addData(satu);

    //     List<Integer> l3 = new ArrayList<Integer>(); 
    //     l3.add(1);  // adds 2
    //     l3.add(3);  // adds 3

    //     satu = new Data('e', 12323, l3);
    //     test.addData(satu);

    //     l3 = new ArrayList<Integer>(); 
    //     l3.add(2);  // adds 2
    //     l3.add(4);  // adds 3
    //     l3.add(5);  // adds 3

    //     satu = new Data('f', 12323, l3);
    //     test.addData(satu);

    //     l3 = new ArrayList<Integer>(); 
    //     l3.add(5);  // adds 3

    //     satu = new Data('g', 12323, l3);
    //     test.addData(satu);

    //     test.insert('a', test.corner, test.corner);

    //     for (Data data : test.allData) {
    //             System.out.println(data.getPosition().toString() + " - " + data.getAlphabet());
    //         }

    //     System.out.println(test.renderString());

    // }
}