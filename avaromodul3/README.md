## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

Nama        : Jauda Avaro Zufaru
NIM         : F1D02410059
Kelompok    : 10

Lakukan analisa dari hasil pengurutan dan pencarian yang telah dilakukan, bandingan hasil setiap algoritma sorting dan searching. Manakah algoritma yang paling efektif dan efisien sesuai dengan data yang ada untuk tiap sorting dan searching.
Jawaban: 
Analisis Algoritma Sorting
Merge Sort menunjukkan performa yang konsisten dan efisien dalam pengujian ini. Dengan waktu eksekusi 0,0721 msec, recursive call sebanyak 99 kali, dan perbandingan 216 kali, Merge Sort membuktikan keunggulannya dalam hal stabilitas dan prediktabilitas. Algoritma ini bekerja dengan membagi data secara rekursif hingga menjadi bagian-bagian terkecil, kemudian menggabungkannya kembali dalam keadaan terurut. Kelebihan utama Merge Sort adalah kompleksitas waktu yang selalu O(n log n) dalam semua kasus (best, average, dan worst case), membuatnya sangat reliable untuk berbagai jenis data.
Quick Sort mencatat waktu sedikit lebih lambat yaitu 0,0789 msec dengan recursive call yang lebih sedikit (33 kali) namun perbandingan yang lebih banyak (358 kali) dan pertukaran sebanyak 318 kali. Quick Sort bekerja dengan memilih pivot dan mempartisi data menjadi dua bagian. Performanya sangat bergantung pada pemilihan pivot yang optimal. Dalam kasus ini, Quick Sort mungkin mengalami ketidakberuntungan dalam pemilihan pivot, menyebabkan partisi yang tidak seimbang dan meningkatkan jumlah perbandingan serta pertukaran.
Untuk dataset yang digunakan dalam pengujian ini, Merge Sort lebih efektif karena waktu eksekusi yang lebih cepat dan jumlah perbandingan yang lebih sedikit, meskipun membutuhkan lebih banyak recursive call.

Analisis Algoritma Searching
Linear Search pada data acak menunjukkan waktu yang sangat cepat yaitu 0,0062 msec dengan 26 perbandingan. Algoritma ini bekerja dengan memeriksa setiap elemen secara berurutan hingga menemukan target. Keunggulan utama Linear Search adalah kesederhanaan implementasi dan tidak memerlukan data terurut. Namun, kompleksitas waktu O(n) membuatnya kurang efisien untuk dataset yang sangat besar.
Binary Search mencatat waktu total 0,1665 msec yang termasuk waktu sorting. Proses searching itu sendiri sangat efisien dengan hanya 1 perbandingan, namun overhead waktu datang dari proses pengurutan data terlebih dahulu menggunakan Merge Sort (99 recursive call, 217 perbandingan). Binary Search dengan kompleksitas O(log n) sangat superior dalam proses pencarian murni, tetapi ketergantungannya pada data terurut menjadi trade-off yang signifikan.
Linear Search lebih efisien untuk pencarian satu kali pada data acak, sementara Binary Search lebih unggul untuk multiple queries pada dataset yang sama setelah dilakukan sorting awal.



Berikan alasan untuk tiap algoritma kenapa bisa lebih cepat dari yang lain atau lebih lambat dari yang lain!
Jawaban: 
Berdasarkan hasil pengujian yang dilakukan terhadap algoritma sorting dan searching, dapat dianalisis secara mendalam alasan-alasan di balik perbedaan performa masing-masing algoritma. Pembahasan ini akan menguraikan faktor-faktor penentu yang membuat suatu algoritma lebih cepat atau lebih lambat dibandingkan dengan lainnya.
Untuk algoritma sorting, Merge Sort mencatat waktu eksekusi yang lebih cepat (0,0721 msec) dibandingkan Quick Sort (0,0789 msec). Keunggulan Merge Sort ini terutama disebabkan oleh karakteristik algoritmiknya yang konsisten dan stabil. Merge Sort memiliki kompleksitas waktu terjamin O(n log n) dalam semua skenario, baik best case, average case, maupun worst case, sehingga tidak bergantung pada kondisi data input. Hal ini terbukti dari jumlah perbandingan yang lebih sedikit (216 kali) dibandingkan Quick Sort (358 kali). Proses Divide-and-Conquer pada Merge Sort yang membagi data hingga menjadi elemen tunggal kemudian menggabungkannya kembali secara terurut ternyata lebih efisien untuk dataset dalam pengujian ini. Selain itu, Merge Sort minim terhadap overhead pertukaran data meskipun memerlukan memori tambahan untuk proses merging.
Di sisi lain, Quick Sort menunjukkan performa yang sedikit lebih lambat karena beberapa faktor kritis. Algoritma ini sangat bergantung pada pemilihan pivot yang optimal. Jika pivot yang terpilih tidak representatif—misalnya elemen terkecil atau terbesar—maka partisi menjadi tidak seimbang dan mengakibatkan peningkatan jumlah perbandingan yang signifikan. Terbukti dari hasil pengujian, Quick Sort melakukan 358 perbandingan, jauh lebih banyak daripada Merge Sort. Faktor lain yang memperlambat Quick Sort adalah tingginya jumlah operasi pertukaran (318 swaps) yang secara komputasional lebih mahal dibandingkan operasi perbandingan. Dalam implementasi nyata, Quick Sort juga rentan terhadap worst-case performance ketika data memiliki pola tertentu atau banyak elemen duplikat, yang mungkin terjadi dalam dataset yang diuji.
Pada sisi algoritma searching, Linear Search menunjukkan kecepatan yang mengesankan (0,0062 msec) untuk pencarian tunggal pada data acak. Keunggulan ini terutama datang dari ketiadaan overhead preparasi—algoritma dapat bekerja langsung pada data asli tanpa perlu proses preprocessing seperti pengurutan. Faktor penting lainnya adalah optimalisasi hardware yang dimanfaatkan Linear Search, khususnya dalam hal lokalitas cache. Dengan mengakses elemen secara berurutan, Linear Search memanfaatkan spatial locality yang memungkinkan prefetching efektif oleh CPU cache. Pola eksekusi yang sederhana juga membuat branch prediction oleh processor bekerja lebih optimal, mengurangi penalty akibat misprediction. Keuntungan tambahan datang dari kemungkinan early termination—jika elemen target berada di posisi awal, pencarian dapat dihentikan segera.
Sebaliknya, Binary Search mencatat waktu total yang lebih lama (0,1665 msec) meskipun secara algoritmik memiliki kompleksitas O(log n) yang lebih efisien. Penyebab utama keterlambatan ini adalah overhead sorting yang dominan, dimana proses pengurutan dengan Merge Sort menyumbang 43% dari total waktu eksekusi. Untuk pencarian tunggal, biaya persiapan ini tidak terkompensasi oleh efisiensi pencarian itu sendiri. Faktor hardware juga berperan penting—Binary Search mengakses elemen secara acak (random access) yang menyebabkan cache misses berulang, berbeda dengan Linear Search yang mengakses data secara sekuensial. Pola control flow yang kompleks pada Binary Search dengan multiple branch possibilities juga menyulitkan branch prediction, leading to pipeline stalls yang mengurangi efisiensi eksekusi.
Dari perspektif yang lebih luas, perbandingan berbagai ukuran data (100, 200, 300 elemen) menunjukkan pola yang konsisten dengan analisis di atas. Linear Search maintain konsistensi waktu yang baik karena sifatnya yang linear, sementara Binary Search menunjukkan fluktuasi waktu yang kuat tergantung efisiensi proses sorting awal. Merge Sort dan Quick Sort juga menunjukkan variasi performa yang mengkonfirmasi karakteristik algoritmik mereka—Merge Sort dengan konsistensi yang baik across different sizes, sementara Quick Sort dengan variabilitas yang lebih tinggi bergantung kondisi data. Secara fundamental, dapat disimpulkan bahwa Merge Sort unggul karena konsistensi algoritmik dan minimasi perbandingan, sementara Quick Sort terhambat oleh ketergantungan pada pivot dan overhead pertukaran.