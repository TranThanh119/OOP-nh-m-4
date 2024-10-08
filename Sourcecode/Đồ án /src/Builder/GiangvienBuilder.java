package builder;

import DoAn.Model.GiangVien;

public class GiangvienBuilder {
	private String id;
	private String ten;
	private String gioitinh;
	private String ngaysinh;

	public GiangvienBuilder setId(String id) {
		this.id = id;
		return this;
	}
	public GiangvienBuilder setTen(String ten) {
		this.ten = ten;
		return this;
	}
	public GiangvienBuilder setGioiTinh(String gioitinh) {
		this.gioitinh = gioitinh;
		return this;
	}
	public GiangvienBuilder setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
		return this;
	}
	public GiangVien build() {
		return new GiangVien(id,ten,gioitinh,ngaysinh);
	}
}
