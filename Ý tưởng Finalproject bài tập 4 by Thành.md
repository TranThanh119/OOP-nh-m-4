## Cấu trức tổng quan (ý tưởng lấy từ chapGPT)
- Main Window: Cửa sổ chính chứa tất cả các thành phần giao diện khác
- TollBar: Chứa các chức năng như tạo thư mục mới, upload, download, chia sẻ, xóa, và tìm kiếm thư mục
- TreeView (Panel bên trái): Hiển thị cấu trúc thư mục dưới dạng cây phân cấp, giúp người dùng dễ dàng điều hướng.
- File Explorer (Panel chính): Hiển thị các file và thư mục trong thư mục hiện tại.
- Detail Pane (Panel bên phải): Hiển thị chi tiết của file hoặc thư mục được chọn (thông tin về tên, kích thước, loại, ngày tạo, thẻ, ...).
- Status Bar (Properties): Hiển thị trạng thái hiện tại của ứng dụng như số lượng file được chọn, dung lượng, trạng thái đồng bộ.
  Về cơ bản nó khá giống với giao diện Window đang sử dụng để quản lý các tệp tin

# Menu Bar 
1. File:
  - New Folder (Tạo thư mục mới)
  - Upload (Tải lên)
  - Download (Tải xuống)
  - Exit (Thoát)
2. Edit:
  -Cut (Cắt)
  -Copy (Sao chép)
  -Paste (Dán)
  -Rename (Đổi tên)
  -Delete (Xóa)
3. View:
  -Refresh (Làm mới)
  -Show Hidden Files (Hiển thị file ẩn)
  -Change View (Thay đổi chế độ xem: danh sách, lưới)
4. Tools:
  -Search (Tìm kiếm nâng cao)
  -Settings (Cài đặt)
  -Compress/Decompress (Nén/Giải nén)
5. Help:
  -User Guide (Hướng dẫn sử dụng)
  -About (Thông tin về ứng dụng)
# ToolBar
. Nút "New Folder": Tạo thư mục mới trong thư mục hiện tại.
. Nút "Upload": Mở hộp thoại chọn file từ hệ thống để tải lên.
. Nút "Download": Tải xuống file hoặc thư mục được chọn.
. Nút "Share": Mở hộp thoại chia sẻ file hoặc thư mục với người khác.
. Nút "Delete": Xóa file hoặc thư mục được chọn.
. Nút "Search": Kích hoạt thanh tìm kiếm để người dùng nhập từ khóa.
# TreeView (Panel bên trái)
1. Hiển thị Cấu trúc Thư mục:
  -Root Directory (Thư mục gốc)
  -Subdirectories (Các thư mục con)
  -File Nodes (Các file trong thư mục)
2. Chức năng Kéo và Thả (Drag & Drop): Cho phép kéo file hoặc thư mục từ TreeView vào các thư mục khác để di chuyển hoặc sao chép.
# File Explorer (Panel chính)
1. Chế độ xem File/Thư mục:
  -View Modes (Danh sách, Lưới)
  -Sort Options (Sắp xếp theo tên, ngày, kích thước, loại)
  -Context Menu (Chuột phải): Chứa các chức năng như Mở, Sao chép, Cắt, Dán, Đổi tên, Xóa.
2. Chức năng Lựa chọn Nhiều: Cho phép người dùng chọn nhiều file hoặc thư mục bằng cách sử dụng Ctrl hoặc Shift.
# Detail Pane (Panel bên phải)
1. Thông tin Chi tiết File/Thư mục:
  -Tên file/thư mục
  -Đường dẫn đầy đủ
  -Kích thước
  -Loại file
  -Ngày tạo, ngày chỉnh sửa cuối
  -Quyền truy cập (Ai có quyền xem, chỉnh sửa)
  -Thẻ (Tags)
2. Tùy chọn Thay đổi Thông tin: Cho phép người dùng đổi tên, thêm thẻ, hoặc chỉnh sửa quyền truy cập trực tiếp từ panel này.
# Status Bar
. Hiển thị trạng thái:
  -Số lượng file/thư mục được chọn
  -Tổng dung lượng file/thư mục được chọn
  -Trạng thái đồng bộ (đồng bộ hóa với đám mây, nếu có)
# Chức năng Khác
1. Cửa sổ Tìm kiếm Nâng Cao: Cho phép tìm kiếm dựa trên nhiều tiêu chí (tên, kích thước, loại file, ngày tạo, v.v.).
2. Hộp thoại Xác nhận: Hiển thị khi người dùng thực hiện các hành động quan trọng như xóa file, di chuyển file, thay đổi quyền truy cập.





