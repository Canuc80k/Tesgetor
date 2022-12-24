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
Vào [java.com](https://www.java.com/ "java.com") tải bản mới nhất về.\
Rồi [cài đặt biến môi trường JAVA_HOME](https://viblo.asia/p/how-to-set-up-java-home-in-window-os-XL6lAvNp5ek "")\
Gì chứ, cái này tui thấy mấy đứa lớp 3 tự mò để chơi minecraft ầm ầm rồi ._.

* #### Bước 2: Chỉnh config
![](https://i.ibb.co/1MQhHvx/Untitled1.png)

 Mục đầu chọn **file sinh input**\
Thứ hai chọn **file sinh output**\
Cái cuối là thư mục sẽ chứa testcase, nhớ là chọn thư mục **rỗng**, đừng chứa mấy file linh tinh khác.

* #### Bước 3: Set up trên file sinh input và output
 **1) Thêm command line argument**: Thay vì **int main()** thì sửa lại thành
 
 \
 ![](https://i.ibb.co/5RGwFZp/Untitled.png)
 
 <br>
 
 **2) Thêm freopen**\
 \
 Ở file sinh input thêm dòng: 
        freopen(argv[1], "w", stdout);
 Ở file sinh output 2 thêm dòng:
		freopen(argv[1], "r", stdin);
		freopen(argv[2], "w", stdout);

 **3)** Nhớ dùng **high_resolution_clock** để sinh seed thay vì **srand**
 
 Tui để 2 file sinh input và output mẫu ở [đây](https://drive.google.com/drive/folders/1SLv9eGQN5Eh60MwKaBsic2K9VwB4bn71?usp=sharing) cho dễ tham khảo.
 
  **4) Sinh test**
  
  ![](https://i.ibb.co/CKbTJGH/Untitled.png)
  
  Ví dụ muốn sinh các testcase có số thứ tự từ 1 -> 10:
  * Nếu nhập [1, 10] các file sinh ra sẽ có dạng: 1, 2, 3, ..., 10
  * Nếu nhập [01, 10] các file sinh ra sẽ có dạng: 01, 02, 03, ..., 10
  * Nếu nhập [001, 100] các file sinh ra sẽ có dạng: 001, 002, 003, ..., 010
 
 Lưu ý là nếu sinh các test mà đã xuất hiện trong testcase folder (hay nói dễ hiểu hơn là sinh các test có trùng số thứ tự), những file test mới sinh sẽ ghi đè lên file cũ. 
 
 
## Những câu hỏi thường gặp
Để [đây](https://docs.google.com/document/d/1w9JIjzzNQg1ZDh0nMADl1f9dTTImvJUBrhCzo2S_EGM/edit?usp=sharing) lần sau có sửa lại gì thì đỡ phải commit :D