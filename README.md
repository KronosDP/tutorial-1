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

# Reflection 2

Setelah membuat beberapa _unit tests_ untuk kode saya, ada beberapa hal yang saya temui dan rasakan.

<details close>
<summary>See More</summary>

Saya merasa cukup yakin dengan code saya. Saya telah mengetesnya sebagai user maupun sebagai _programmer_ yang iseng. Saya mencoba untuk memasukkan _quantity_ yang negatif maupun yang tidak bersifat _integer_. Karena program saya belum _meng-handle_ kasus tersebut, maka saya juga sekalian mengurusnya ketika mengurus unit test.

Menurut saya, tidak ada angka yang pasti untuk jumlah _unit test_ pada sebuah _class_. Walaupun demikian, menurut saya ada baiknya jika jumlah _unit test_ dan fungsi yang ada pada di sebuah kelas mirip jumlahnya, ataupun _unit test_-nya dibuat lebih banyak. Hal ini untuk memastikan program yang telah dibuat sudah _robust_.

Jika sebuah kode punya 100% _code coverage_ menurut saya bug bisa saja tetap terjadi. Walaupun demikian, kemungkinannya kecil karena _unit test_ sudah mengecek banyak aspek dari kode. Intinya adalah tidak ada jaminan bahwa kode yang telah dibuat tidak memiliki _bug_ atau _error_.

Jika saya diminta untuk mengecek jumlah _item_ pada product list, saya rasa membuat kelas baru adalah hal yang _redundant_ untuk dilakukan. Menurut saya, kita bisa langsung membuat seperti yang saya buat, yaitu menekan tombol submit dan mengecek berapa item yang telah ada di tabel. Menurut saya dengan membuatnya seperti itu, kode akan tetap bersih dan mudah untuk di-trace karena tidak ada terlalu banyak hal yang perlu diuji.

</details>
