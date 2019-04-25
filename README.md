# Announcement
# Conflict-free Replicated Data Type (CRDT)

## Petunjuk penggunaan program

1. 

## Pembagian tugas

Nicholas Thie / 13515079 (33%) - Messaging, Controller, Laporan.  
Albert Sahala Theodore / 13516022 (33%) - GUI, Controller, Laporan.<br />
Hagai Raja Sinulingga / 13516136 (33%) - CRDT, Controller, Laporan.

## Laporan pengerjaan

### Cara Kerja Program


### Penjelasan Fungsi
> CRDT

Implementasi CRDT secara lengkap dapat dilihat pada file `CRDT.java`. Kami melakukan penyimpanan data menggunakan struktur data tree yang diimplementasikan dalam bentuk array of List. Untuk menjamin setiap perubahan pada dokumen tetap menjamin kaidah komutatif dan idempoten, kami melakukannya dengan membuat ID global untuk setiap huruf dan posisi global yang digenerate setiap kali sebuah node melakukan perubahan ke dokumen. CRDT sangat erat kaitannya dengan Version Vector dan Deletion Buffer.

Kami mengimplementasikan dua fungsi CRDT yang dapat dipanggil controller yakni `insert` dan `delete`. Keduanya masing-masing memiliki tipe yang local dan global. Bagi yang global tidak terlalu membutuhkan penanganan khusus tetapi untuk local perlu strategi sehingga saat di broadcast ke node yang lain, kemungkinan kesalahannya menjadi kecil. Salah satu strategi yang kami lakukan adalah membuat jarak antara counter setiap node. Hal ini membuat setiap global ID dari huruf yang diajukan sebuah node pasti unik tanpa harus mencoba beberapa kali untuk menyamakan angka yang tepat di semua node.

Intinya fungsi insert adalah menjamin data yang tersimpan dalam daftar huruf global benar dan sesuai urutan. Sedangkan untuk fungsi delete adalah menghapus data tepat sesuai ID global yang sesuai dengannya. Jika tidak ada, data inilah yang masuk ke dalam deletion buffer.

> Version Vector

Kami mengimplementasikan version vector lewat counter yang selalu menaik apabila terdapat perubahan baik itu penambahan maupun penghapusan data. Counter ini akan dikirim setiap kali melakukan broadcast sehingga setiap penerima pesan dapat melakukan tracking terhadap data yang diterimanya (walaupun pada kenyataannya kami mengimplementasikan messagingnya menggunakan protokol TCP sehingga tidak mengalami masalah demikian).	

> Deletion Buffer

### Kasus Uji


### Screenshot Hasil Program

