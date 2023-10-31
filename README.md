#  tesgetor

# Lời cảm ơn: 
gửi lời cảm ơn chân thành nhất đến [@CarlavierVN](https://github.com/CarlavierVN) vì đã dành nhiều ngày cuối tuần làm QC không lương >_<

![](https://img.shields.io/badge/java-1.8%2B-orange) ![](https://img.shields.io/badge/window-7%2B-blue)

<br/>

#  Tính năng

Sinh test

<br/>
  

#  Thì biết rồi, nhưng sinh test như nào .-.

  

Phần mềm sẽ chạy [**file sinh input**] và [**file sinh output**] nhiều lần để tạo ra các testcase.

Hiện tại, chỉ mới hỗ trợ các [**file sinh**] dùng ngôn ngữ C++.

![](https://i.ibb.co/vxKSpnr/a.png)

ㅤ

#  Cài đặt

* ##  Bước 1: Tải và cài đặt Java

&emsp;&emsp;Truy cập [java.com](https://www.java.com/en/download/ "java.com") và tải bản Java mới nhất.

&emsp;&emsp;Rồi [cài đặt biến môi trường JAVA_HOME](https://viblo.asia/p/how-to-set-up-java-home-in-window-os-XL6lAvNp5ek "")

&emsp;&emsp;Gì chứ, cái này tui thấy mấy đứa lớp 3 tự mò để chơi minecraft ầm ầm rồi ._.

 * ##  Bước 2: Thêm Path cho MinGW 
 &emsp;&emsp;Để cái [link hướng dẫn](https://www.youtube.com/watch?v=mQra00mT3Dg) ở đây cho dễ :b<br/>
 &emsp;&emsp;Nhớ là phải restart máy sau khi thêm path<br/>
 &emsp;&emsp;Và phải nhớ chọn bản MinGW nào hỗ trợ C++ 11<br/>

* ##  Bước 3: Chỉnh config

&emsp;&emsp;![](https://i.ibb.co/1MQhHvx/Untitled1.png)

  

&emsp;&emsp;Mục đầu chọn [**file sinh input**]

&emsp;&emsp;Thứ hai chọn [**file sinh output**]

&emsp;&emsp;Cái cuối là thư mục sẽ chứa các testcase sinh ra, khuyến khích chọn một thư mục **rỗng**.

  

* ##  Bước 4: Set up trên file sinh input và output

&emsp;&emsp;**1) Thêm command line argument**:

&emsp;&emsp;Thay vì **int main()** thì sửa lại thành

&emsp;&emsp; ![](https://i.ibb.co/5RGwFZp/Untitled.png)

&emsp;&emsp;**2) Thêm freopen**

&emsp;&emsp;Ở file sinh input thêm dòng:

&emsp;&emsp;  ``` freopen(argv[1], "w", stdout); ```

&emsp;&emsp;

  

&emsp;&emsp;Ở file sinh output 2 thêm dòng:<br/>

&emsp;&emsp;```freopen(argv[1], "r", stdin);```<br/>

&emsp;&emsp;```freopen(argv[2], "w", stdout);```<br/>

<br/>

  

&emsp;&emsp;**3) Sử dụng random C++ 11** <br/>
&emsp;&emsp;Nhớ dùng **high_resolution_clock** để sinh seed thay vì **srand**<br/>
&emsp;&emsp;Tui để 2 file sinh input và output mẫu ở [đây](https://drive.google.com/drive/folders/1SLv9eGQN5Eh60MwKaBsic2K9VwB4bn71?usp=sharing) cho dễ tham khảo.<br/>


* ## Bước 5: Sinh test

&emsp;&emsp;![](https://i.ibb.co/CKbTJGH/Untitled.png)

&emsp;&emsp;Ví dụ muốn sinh các testcase có số thứ tự từ 1 -> 10:<br/>

&emsp;&emsp; * Nếu nhập [1, 10] các file sinh ra sẽ có dạng: 1, 2, 3, ..., 10<br/>

&emsp;&emsp; * Nếu nhập [01, 10] các file sinh ra sẽ có dạng: 01, 02, 03, ..., 10<br/>

&emsp;&emsp; * Nếu nhập [001, 010] các file sinh ra sẽ có dạng: 001, 002, 003, ..., 010<br/>

&emsp;&emsp;Lưu ý là nếu sinh các test mà đã xuất hiện trong testcase folder (hay nói dễ hiểu hơn là sinh các test có trùng số thứ tự), những file test mới sinh sẽ ghi đè lên file cũ.

##  Những câu hỏi thường gặp

Để [đây](https://docs.google.com/document/d/1w9JIjzzNQg1ZDh0nMADl1f9dTTImvJUBrhCzo2S_EGM/edit?usp=sharing) lần sau có sửa lại gì thì đỡ phải commit :D
