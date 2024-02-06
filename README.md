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

Menurut pendapat saya, fungsi yang telah saya buat ini memiliki ukuran yang cukup ideal. Fungsi yang telah saya buat dibuat dengan prinsip _Single Responsibility_, di mana setiap fungsi hanya melakukan satu tugas spesifik. Hal ini membantu dalam mempertahankan _readibility_ dan _code maintainability_. Selain itu, saya telah memberikan nama yang deskriptif untuk fungsi yang telah saya buat. Nama yang deskriptif ini memudahkan pemahaman tentang apa yang sebenarnya dilakukan oleh fungsi tersebut tanpa harus membaca detail implementasinya. Fungsi ini juga dirancang sedemikian rupa sehingga tidak memiliki _side effect_ bagi program secara keseluruhan. Hal tersebut berarti bahwa fungsi ini tidak mengubah status atau kondisi dari bagian lain dari program. Hal ini sangat penting dalam pemrograman fungsional dan membantu dalam meminimalkan bug dan kesalahan. Fungsi ini melakukan operasi _create_, _edit_, dan _delete_. Hal menarik yang telah saya buat adalah bahwa ketiga operasi ini dilakukan dalam _class_ yang sama, yang berarti bahwa mereka mungkin berinteraksi dengan data yang sama dan oleh karena itu harus ditangani dengan hati-hati.

Menurut saya, saya telah menerapkan prinsip `Object and Data Structures` dalam kode yang telah saya tulis. Saya telah membuat abstraksi dalam bentuk _interface_. Abstraksi ini memungkinkan kita untuk menyembunyikan detail implementasi yang kompleks dan hanya mengekspos metode dan properti yang diperlukan. Ini membantu dalam mempertahankan modularitas dan fleksibilitas kode. Selanjutnya, saya menggunakan atribut yang disimpan secara `private` dalam _class_ yang ada. Untuk mengakses atau memodifikasi atribut `private` ini, saya menggunakan metode _setter_ dan _getter_. Metode _setter_ dan _getter_ ini bertindak sebagai antarmuka untuk atribut `private`, memungkinkan kita untuk mengontrol bagaimana atribut ini diakses dan dimodifikasi.

## Prinsip yang Belum Diterapkan

Berikut adalah prinsip _clean code_ yang belum saya terapkan.

- Comments
- Error Handling

Sampai sekarang, saya belum menuliskan komentar apapun pada kode saya. Hal ini karena saya rasa kode saya sudah cukup _concise_. Menurut saya, karena kode saya belum panjang dan masih sangat mudah dilakukan _tracing_ dan karena saya menggunakan nama yang jelas, comments tidak perlu dilakukan. Jika kedepannya _comments_ perlu dilakukan karena kompleksitas kode bertambah, saya akan menambahkannya. Selain itu, _error handling_ belum saya lakukan. Hal ini karena menurut saya _error handling_ belum diperlukan. Hal ini dikarenakan kode saya pada bagian _create_, _edit_, dan _delete_ seharusnya tidak memunculkan error. Walaupun di bagian _findById_ dan _update_ kemungkinan terjadi error karena mengembalikan _null_, tetapi karena kita mengirimkan _id_ yang pasti ada, seharusnya method _findById_ dan _update_ tidak akan pernah mengembalikan _null_. Jika ada kemungkinan bahwa kedua fungsi tersebut mengembalikan null, saya mungkin akan mencoba _throw_, _try_, dan _catch_.

</details>

<br>

# Reflection 2

Setelah membuat beberapa _unit tests_ untuk kode saya, ada beberapa hal yang saya temui dan rasakan.

<details close>
<summary>See More</summary>

Saya merasa cukup yakin dengan code saya. Saya telah mengetesnya sebagai user maupun sebagai _programmer_ yang _iseng_. Saya mencoba untuk memasukkan _quantity_ yang negatif maupun yang tidak bersifat _integer_. Karena program saya belum _meng-handle_ kasus tersebut, maka saya juga sekalian mengurusnya ketika mengurus _unit test_. Saya mengurusnya dengan cara _throw exception_ di dalam program agar program benar-benar jelas mengalami masalah apa.

Menurut saya, tidak ada angka yang pasti untuk jumlah _unit test_ pada sebuah _class_. Walaupun demikian, menurut saya ada baiknya jika jumlah _unit test_ dan fungsi yang ada pada di sebuah kelas mirip jumlahnya, ataupun _unit test_-nya dibuat lebih banyak. Hal ini untuk memastikan program yang telah dibuat sudah _robust_. Dengan memastikan program yang telah dibuat adalah program yang _robust_, kita bisa lebih percaya dengan program kita. Hal ini oleh karena program kita sebenarnya "diawasi" dengan _unit test_ yang telah kita buat, yang harapannya jika ada perubahan di masa mendatang, perubahan yang kita buat tidak merusak program yang ada.

Jika sebuah kode punya 100% _code coverage_ menurut saya bug bisa saja tetap terjadi. Walaupun demikian, kemungkinannya kecil karena _unit test_ sudah mengecek banyak aspek dari kode. Intinya adalah tidak ada jaminan bahwa kode yang telah dibuat tidak memiliki _bug_ atau _error_. Hal ini dikarenakan _code coverage_ bukanlah sebuah adalah pennjamin bahwa kode yang telah dibuat bebas dari _bug_ atau _error_. Melainkan, _code coverage_ yang luas adalah penjamin bahwa kita sudah mencoba semaksimal mungkin untuk memastikan code kita sudah kita coba untuk buat sedemikian rupa sehingga tidak ada _bug_ atau _error_ yang dilewatkan.

Jika saya diminta untuk mengecek jumlah _item_ pada product list, saya rasa membuat kelas baru adalah hal yang _redundant_ untuk dilakukan. Menurut saya, kita bisa langsung membuat seperti yang saya buat, yaitu menekan tombol submit dan mengecek berapa item yang telah ada di tabel. Menurut saya dengan membuatnya seperti itu, kode akan tetap bersih dan mudah untuk di-trace karena tidak ada terlalu banyak hal yang perlu diuji. Dengan demikian, saya rasa mudah untuk kita kembali ke sebuah proyek dan mulai mengerjakannya. Menurut saya, semakin mudah untuk sebuah program menarik kita untuk mengerjakannya artinya kode tersebut sudah semakin baik dibuat karena sudah menarik kita untuk mengerjakannya.

Terakhir, saya ingin mengoreksi kode saya tentang membuat _unit test_ jika kita memaksa suatu atribut pada product untuk bernilai _null_. Saya mendesain program saya sedemikian rupa sehingga program saya melemparkan sebuah _exception_.

</details>
