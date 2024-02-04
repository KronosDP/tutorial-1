# Reflection 1

Setelah melakukan review saya, saya merasa saya sudah melaksanakan hal-hal yang dipelajari di kelas.

Menurut saya, saya sudah menulis kode dengan menggunakan prinsip-prinsip `clean code`. Namun demikian, ada beberapa prinsip yang belum saya lakukan.

<details close>
<summary>See More</summary>

## Prinsip yang Sudah Diterapkan

Prinsip-prinsip clean code yang saya sudah terapkan adalah:

- Meaningful names
- Functions
- Objects and Data Structures

Menurut saya, fungsi yang saya buat sudah cukup pendek dan hanya melakukan 1 hal. Selain itu, nama fungsi yang saya buat sudah cukup deskriptif dan tidak memiliki _side effect_ bagi program secara keseluruhan karena melakukan _create_, _edit_, dan _delete_, dimana ketiga hal tersebut dilakukan di class yang sama, oleh karena itu seharusnya hal code ini mudah untuk di-debug jika ada masalah.

Menurut saya, saya telah menggunakan prinsip `Object and Data Structures` pada kode yang telah saya buat. Saya sudah membuat abstraksi yaitu interface dan saya menggunakan atribut yang disimpan secara `private` pada sebuah kelas yang tidak bisa diakses secara mudah, melainkan perlu menggunakan setter dan getter.

## Prinsip yang Belum Diterapkan

Berikut adalah prinsip _clean code_ yang belum saya terapkan.

- Comments
- Error Handling

Sampai sekarang, saya belum menuliskan komentar apapun pada kode saya. Hal ini karena saya rasa kode saya sudah cukup _concise_. Menurut saya, karena kode saya belum panjang dan masih sangat mudah dilakukan _tracing_ dan karena saya menggunakan nama yang jelas, comments tidak perlu dilakukan. Jika kedepannya comments perlu dilakukan karena kompleksitas kode bertambah, saya akan menambahkannya.

Selain itu, _error handling_ belum saya lakukan. Hal ini karena menurut saya _error handling_ belum diperlukan. Hal ini dikarenakan kode saya pada bagian _create_, _edit_, dan _delete_ seharusnya tidak memunculkan error. Walaupun di bagian _findById_ dan _update_ kemungkinan terjadi error karena mengembalikan _null_, tetapi karena kita mengirimkan _id_ yang pasti ada, seharusnya method _findById_ dan _update_ tidak akan pernah mengembalikan _null_. Jika ada kemungkinan bahwa kedua fungsi tersebut mengembalikan null, saya mungkin akan mencoba _throw_, _try_, dan _catch_.

</details>
