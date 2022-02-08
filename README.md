# Project Analyzing HTML

## Cấu hình project 
- Project sử dụng cấu hình
  - Java 11
  - Spring 2.6.3

## Miêu tả yêu cầu
- <b>Request 00</b>:
  - Api translate sử dụng API: https://developer.oxforddictionaries.com/documentation
  - acc: `truongnv0590`
  - pass: `Abcd1234.`
  - app_id: `76e4f61d`
  - app_key: `8d179a4f58ddf109d5c386c5898211d3` 
  - cURL sample:
  ```
  curl --request POST \
    --url http://127.0.0.1:8080/api/v1/translate \
    --header 'Content-Type: application/json' \
    --data '{
    "language": "en-us",
    "word": "test",
    "fields": "pronunciations",
    "strictMatch": "false"
  }'
  ```
- <b>Request 01</b>:
  - Từ một đoạn HTML với encoding UTF8, phân tích:
      - Đếm số ký tự
      - Đếm số từ
      - Đếm số câu
  
  - Thí dụ một đoạn HTML cắt từ [vnexpress](https://vnexpress.net/tong-giam-doc-starbucks-viet-nam-ly-nuoc-90-000-dong-thanh-thuc-uong-hang-ngay-4412396.html)
    ```
    <h1 class=\"title-detail\">Tổng giám đốc Starbucks Việt Nam: 'Ly nước 90.000 đồng thành thức uống hàng ngày'</h1>
    <p class=\"description\">Tổng giám đốc Starbucks Việt Nam xác nhận kết quả tài chính năm 2021 không bằng trước dịch nhưng ly nước 90.000 đồng của họ đã thành \"cà phê hàng ngày\".</p>
    <p class=\"Normal\">\"Nói về những con số thì kết quả kinh doanh năm qua không thể bằng 2020 và rất xa so với những năm bình thường\", bà Patricia Marques, Tổng giám đốc Starbucks Việt Nam chia sẻ mới đây.</p>
    <p class=\"Normal\">Không nói con số cụ thể nhưng bà Patricia cho rằng, đây là điều dễ hiểu vì năm 2020 giãn cách chỉ khoảng hai tuần, còn năm qua tổng thời gian giãn cách kéo dài chín tuần, trong đó có giai đoạn hoàn toàn không kinh doanh gì ở nhiều cửa hàng.</p>
    <p class=\"Normal\">Dù thừa nhận kết quả tài chính thấp, Starbucks vẫn \"ăn nên làm ra\" ở một số mặt khác. Đó là chuỗi đã xây dựng được tập khách hàng thường xuyên, chấp nhận một ly nước giá 90.000-100.000 đồng. \"Starbucks đến thời điểm này có thể nói đã trở thành cà phê hàng ngày của khách hàng rồi\", bà Patricia tự tin.</p>"
    ```  
    => Kết quả cần ra đúng
    ```
    Số ký tự: 959 (????? - Thực tế check bằng nhiều tool không ra kết quả này)
    Số từ: 198
    Số câu: 7
    ```
  - todo:
    - Phân tích API của `oxforddictionaries`
    - tìm thư viện clear tag HTML
    - impl API count 
  
  - cURL sample: 
  ```
  curl --request POST \
    --url http://127.0.0.1:8080/api/v1/count-all \
    --header 'Content-Type: application/json' \
    --data '{
    "html_string": "<h1 class=\"title-detail\">Tổng giám đốc Starbucks Việt Nam: '\''Ly nước 90.000 đồng thành thức uống hàng ngày'\''</h1><p class=\"description\">Tổng giám đốc Starbucks Việt Nam xác nhận kết quả tài chính năm 2021 không bằng trước dịch nhưng ly nước 90.000 đồng của họ đã thành \"cà phê hàng ngày\".</p><p class=\"Normal\">\"Nói về những con số thì kết quả kinh doanh năm qua không thể bằng 2020 và rất xa so với những năm bình thường\", bà Patricia Marques, Tổng giám đốc Starbucks Việt Nam chia sẻ mới đây.</p><p class=\"Normal\">Không nói con số cụ thể nhưng bà Patricia cho rằng, đây là điều dễ hiểu vì năm 2020 giãn cách chỉ khoảng hai tuần, còn năm qua tổng thời gian giãn cách kéo dài chín tuần, trong đó có giai đoạn hoàn toàn không kinh doanh gì ở nhiều cửa hàng.</p><p class=\"Normal\">Dù thừa nhận kết quả tài chính thấp, Starbucks vẫn \"ăn nên làm ra\" ở một số mặt khác. Đó là chuỗi đã xây dựng được tập khách hàng thường xuyên, chấp nhận một ly nước giá 90.000-100.000 đồng. \"Starbucks đến thời điểm này có thể nói đã trở thành cà phê hàng ngày của khách hàng rồi\", bà Patricia tự tin.</p>"
  }'
  ```

- <b>Request 02</b>:
  - Chuyển đổi định dạng text markdown sang HTML:
    - support heading (1-6)
    - in đậm
    - in nghiêng
    - gạch chân
  - cURL sample:
  ```
  curl --request POST \
    --url http://127.0.0.1:8080/api/v1/md-to-html \
    --header 'Content-Type: application/json' \
    --data '{
    "md_string": "# abc *a* **a**"
  }'
  ```
