create database my_project;
use my_project;
create table monhoc(
	ma_monhoc int not null auto_increment,
    ten_monhoc varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    primary key (ma_monhoc)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table giaovien(
	ma_giaovien int not null auto_increment,
    ho varchar(20) COLLATE utf8_unicode_ci not null,
    ten varchar(15) COLLATE utf8_unicode_ci not null,
     primary key (ma_giaovien)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table sinhvien(
	ma_sinhvien int not null auto_increment,
    ho varchar(20) COLLATE utf8_unicode_ci not null,
    ten varchar(15) COLLATE utf8_unicode_ci not null,
	primary key (ma_sinhvien)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table lop(
	ma_lop int not null auto_increment,
    ten_lop varchar (45) COLLATE utf8_unicode_ci not null,
    ma_monhoc int not null,
    ngay_batdau Date not null,
    ngay_ketthuc Date not null,
    so_gio_hoc int not null,
	primary key (ma_lop),
	constraint fk_maMH
		foreign key (ma_monhoc)
        references monhoc(ma_monhoc)
        on delete no action
        on update cascade
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table giaovien_lop(
	ma_giaovien int not null,
    ma_lop int not null,
    primary key (ma_giaovien,ma_lop),
    constraint fk_maGV
		foreign key (ma_giaovien)
        references giaovien(ma_giaovien)
		on delete no action
        on update cascade,
	 constraint fk_maLop
		foreign key (ma_lop)
        references lop(ma_lop)
		on delete no action
        on update cascade
);

create table sinhvien_lop(
	ma_sinhvien int not null,
    ma_lop int not null,
    primary key (ma_sinhvien,ma_lop),
	constraint fk_maSV
		foreign key (ma_sinhvien) references sinhvien(ma_sinhvien)
        on delete no action
        on update cascade,
	constraint
		foreign key (ma_lop) references lop(ma_lop)
        on delete no action
        on update cascade
);
