

# dont_generatetest_me_pls
![](https://img.shields.io/badge/java-1.8%2B-orange) ![](https://img.shields.io/badge/window-7%2B-blue)

## Tính năng
	Sinh test 

## Thì biết rồi, nhưng sinh test như nào .-.

Phần mềm sẽ sử dụng file sinh input và output của người dùng để sinh ra các testcase.\
Hiện tại, chỉ mới hỗ trợ file sinh test dùng ngôn ngữ C++.
![](https://i.ibb.co/RgLX0rT/Untitled.png)
ㅤ
## Cài đặt
* #### Bước 1: Tải Java  
&emsp;&emsp;Vào [java.com](https://www.java.com/ "java.com") tải bản mới nhất về.\
&emsp;&emsp;Rồi [cài đặt biến môi trường JAVA_HOME](https://viblo.asia/p/how-to-set-up-java-home-in-window-os-XL6lAvNp5ek "")\
&emsp;&emsp;Gì chứ, cái này tui thấy mấy đứa lớp 3 tự mò để chơi minecraft ầm ầm rồi ._.

* #### Bước 2: Chỉnh config
&emsp;&emsp;![](https://i.ibb.co/1MQhHvx/Untitled1.png)

&emsp;&emsp;Mục đầu chọn **file sinh input**\
&emsp;&emsp;Thứ hai chọn **file sinh output**\
&emsp;&emsp;Cái cuối là thư mục sẽ chứa testcase, nhớ là chọn thư mục **rỗng**, đừng chứa mấy file linh tinh khác.

* #### Bước 3: Set up trên file sinh input và output
&emsp;&emsp;**1) Thêm command line argument**:<br/>
&emsp;&emsp;Thay vì **int main()** thì sửa lại thành
 
 &emsp;&emsp; ![](https://i.ibb.co/5RGwFZp/Untitled.png)
 
 <br/>
 
&emsp;&emsp;**2) Thêm freopen**<br/>
 &emsp;&emsp;Ở file sinh input thêm dòng: <br/> 
&emsp;&emsp; ``` freopen(argv[1], "w", stdout); ```
&emsp;&emsp;<br/>

&emsp;&emsp;Ở file sinh output 2 thêm dòng:<br/>
&emsp;&emsp;```freopen(argv[1], "r", stdin);```<br/>
&emsp;&emsp;```freopen(argv[2], "w", stdout);```<br/>
<br/>

&emsp;&emsp;**3)** Nhớ dùng **high_resolution_clock** để sinh seed thay vì **srand**
 
&emsp;&emsp;Tui để 2 file sinh input và output mẫu ở [đây](https://drive.google.com/drive/folders/1SLv9eGQN5Eh60MwKaBsic2K9VwB4bn71?usp=sharing) cho dễ tham khảo.
 <br/>
 * #### Bước 4: Sinh test  
  
  &emsp;&emsp;![](https://i.ibb.co/CKbTJGH/Untitled.png)
  
  &emsp;&emsp;Ví dụ muốn sinh các testcase có số thứ tự từ 1 -> 10:<br/>
  &emsp;&emsp; * Nếu nhập [1, 10] các file sinh ra sẽ có dạng: 1, 2, 3, ..., 10<br/>
  &emsp;&emsp; * Nếu nhập [01, 10] các file sinh ra sẽ có dạng: 01, 02, 03, ..., 10<br/>
  &emsp;&emsp; * Nếu nhập [001, 010] các file sinh ra sẽ có dạng: 001, 002, 003, ..., 010<br/>
 
 &emsp;&emsp;Lưu ý là nếu sinh các test mà đã xuất hiện trong testcase folder (hay nói dễ hiểu hơn là sinh các test có trùng số thứ tự), những file test mới sinh sẽ ghi đè lên file cũ. 
 
 
## Những câu hỏi thường gặp
Để [đây](https://docs.google.com/document/d/1w9JIjzzNQg1ZDh0nMADl1f9dTTImvJUBrhCzo2S_EGM/edit?usp=sharing) lần sau có sửa lại gì thì đỡ phải commit :D
