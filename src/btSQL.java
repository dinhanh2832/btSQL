public class btSQL {
    # BÀI TẬP ÔN TẬP SQL
# Bài quản lý sản phẩm
# Mức 1:
            # In ra các sản phẩm có số lượng từ 30 trở lên.
    use demo20062;
    select name
    from product
    where quantity >= 30;
# In ra các sản phẩm có số lượng từ 30 trở lên và có giá trong khoảng 100 đến 200.
    use demo20062;
    select name
    from product
    where quantity >= 30
    and (price between 100 and 300);
# In ra thông tin khách hàng tuổi lớn hơn 18
    use demo20062;
    select name, age
    from customer
    where age > 18;
# In ra thông tin khách hàng họ Nguyễn và lớn hơn 20 tuổi
    use demo20062;
    select name, age
    from customer
    where age > 20
    and name like '%nguyễn%';
# In ra sản phẩm tên bắt đầu bằng chữ M
    use demo20062;
    select name
    from product
    where name like 'm%';
# In ra sản phẩm kết thức bằng chữ E
    use demo20062;
    select name
    from product
    where name like '%E';
# In ra danh sách sản phẩm số lượng tăng dần
    use demo20062;
    select id, name, price, quantity
    from product
    order by quantity;
# In ra danh sách sản phẩm giá giảm dần
    use demo20062;
    select id, name, price, quantity
    from product
    order by quantity desc;
# Mức 2:
            # In ra tổng số lượng sản phẩm giá nhỏ hơn 300
    use demo20062;
    select sum(quantity)
    from product
    where price < 300;
# In tổng số sản phẩm theo từng giá
    use demo20062;
    select price, count(id)
    from product
    group by price;
# In ra sản phẩm có giá cao nhất
    use demo20062;
    select name, max(price)
    from product;
# In ra giá trung bình của tất cả các sản phẩm
    use demo20062;
    select avg(price)
    from product;
# In ra tổng số tiền nếu bán hết tất cả sản phẩm.
    use demo20062;
    select sum(price * product.quantity)
    from product;
# Tính tổng số sản phẩm giá <300.
    use demo20062;
    select count(id)
    from product
    where price < 300;
# Tìm giá bán cao nhất của các sản phẩm bắt đầu bằng chữ M.
    use demo20062;
    select name, max(price)
    from product
    where name like 'M%';
# Thấp nhất của các sản phẩm bắt đầu bằng chữ M.
            use demo20062;
    select name, min(price)
    from product
    where name like 'M%';
# Trung bình của các sản phẩm bắt đầu bằng chữ M.
            use demo20062;
    select avg(price)
    from product
    where name like 'M%';
# Mức 3:
            # In ra tên khách hàng và thời gian mua hàng.
    use demo20062;
    select name, time
    from product
    join orderdetail on product.id = orderdetail.productId
    join `order` on `order`.id = orderdetail.orderId;
# In ra tên khách hàng và tên sản phẩm đã mã
    use demo20062;
    select customer.name, product.name
    from customer
    join `order` on customer.id = `order`.customerId
    join orderdetail on orderdetail.orderId = `order`.id
    join product on product.id = orderdetail.productId
    group by customer.name;
# In ra mã hoá đơn và giá trị hoá đơn
    use demo20062;
    select `order`.id, sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.id;
# In ra mã hoá đơn và giá trị hoá đơn giảm dần
    use demo20062;
    select `order`.id, sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.id
    order by giatri;
# Mức 4-10 :
            # In ra các mã hóa đơn, trị giá hóa đơn bán ra trong ngày 19/6/2006 và ngày 20/6/2006.
    use demo20062;
    select `order`.id, sum(product.price * orderdetail.quantity)
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where `order`.time = '2006-06-20'
    or `order`.time = '2006-06-19'
    group by `order`.id;
# In ra các mã hóa đơn, trị giá hóa đơn trong tháng 6/2006, sắp xếp theo ngày (tăng dần) và trị giá của hóa đơn (giảm dần).
    use demo20062;
    select `order`.id, sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where month(`order`.time) = 6
    and year(`order`.time) = 2006
    group by product.name
    order by giatri desc;
# In ra danh sách các khách hàng (MAKH, HOTEN) đã mua hàng trong ngày 19/06/2007.
    use demo20062;
    select customer.id, customer.name
    from customer
    join `order` on customer.id = `order`.customerId
    join orderdetail on `order`.id = orderdetail.orderId
    where time = '2007-06-19'
    group by customer.id;
# In ra danh sách các sản phẩm (MASP, TENSP) được khách hàng có tên “Nguyen Van A” mua trong tháng 10/2006.
    use demo20062;
    select product.id, product.name
    from product
    join orderdetail on product.id = orderdetail.productId
    join `order` on orderdetail.orderId = `order`.id
    join customer on customer.id = `order`.customerId
    where customer.name = 'Nguyễn Văn A'
    and (MONTH(`order`.time) = 10 and year(`order`.time) = 2006);

# Tìm các số hóa đơn đã mua sản phẩm “Máy giặt” hoặc “Tủ lạnh”.
    use demo20062;
    select `order`.id
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where product.name = 'Máy giặt'
    or product.name = 'Tử lạnh'
    group by `order`.id;
# In ra danh sách các sản phẩm (MASP, TENSP) không bán được.
    use demo20062;
    select product.id, product.name
    from product
    where product.id not in (select orderdetail.productId from orderdetail)
    group by name;
# In ra danh sách các sản phẩm (MASP, TENSP) không bán được trong năm 2006.
    use demo20062;
    select product.id, product.name
    from product,
     `order`
    where product.id not in (select orderdetail.productId from orderdetail)
    and year(`order`.time) = 2006;

# In ra danh sách các sản phẩm (MASP, TENSP) có giá >=300 sản xuất bán được trong năm 2006.
    use demo20062;
    select product.id, product.name
    from product,
     `order`
    where (product.id in (select orderdetail.productId from orderdetail) and year(`order`.time) = 2006)
    and product.price >= 300
    group by name;
# Có bao nhiêu sản phẩm khác nhau được bán ra trong năm 2006.
    use demo20062;
    select count(name)
    from (select product.name
            from product,
           `order`
                  where product.id in (select orderdetail.productId from orderdetail)
    and year(`order`.time) = 2006
    group by product.name) as tabl;

# Tìm các số hóa đơn đã mua sản phẩm “Máy giặt” hoặc “Tủ lạnh”, mỗi sản phẩm mua với số lượng từ 10 đến 20.
    use demo20062;
    select `order`.id, product.name, orderdetail.quantity
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where (product.name = 'Máy giặt' or product.name = 'Tủ Lạnh')
    and orderdetail.quantity between 10 and 20;

# Tìm các số hóa đơn mua cùng lúc 2 sản phẩm “Máy giặt” và “Tủ lạnh”, mỗi sản phẩm mua với số lượng từ 10 đến 20.
    use demo20062;
    select `order`.id
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where (product.name = 'Máy giặt' or product.name = 'Tủ Lạnh')
    and orderdetail.quantity between 10 and 20
    group by `order`.id
    having count(product.id) >= 2;

# Tìm số hóa đơn đã mua tất cả các sản phẩm có giá >200.
    use demo20062;
    select `order`.id, product.name, product.price
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where product.price > 200
    group by `order`.id;

# Tìm số hóa đơn trong năm 2006 đã mua tất cả các sản phẩm có giá <300.
    use demo20062;
    select `order`.id, product.name, product.price
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where product.price < 300
    and year(`order`.time) = 2006
    group by `order`.id;
# Có bao nhiêu sản phẩm khác nhau được bán ra trong năm 2006.
    use demo20062;
    select count(name) as sosanpham
    from (select product.name
            from product,
           `order`
                  where product.id in (select orderdetail.productId from orderdetail)
    and year(`order`.time) = 2006
    group by product.name) as tabl;
# Cho biết trị giá hóa đơn cao nhất, thấp nhất là bao nhiêu?
    use demo20062;
    select max(giatri), min(giatri)
    from (select `order`.id, sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.id) as maxmin;

# Trị giá trung bình của tất cả các hóa đơn được bán ra trong năm 2006 là bao nhiêu?
    use demo20062;
    select avg(giatri)
    from (select sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.id) as tabavg;

# Tính doanh thu bán hàng trong năm 2006.
    use demo20062;
    select sum(product.price * orderdetail.quantity) as doanhso2006
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where year(`order`.time) = 2006;

# Tìm số hóa đơn có trị giá cao nhất trong năm 2006.
    use demo20062;
    select id
    from (select `order`.id, sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.id) doanhthu
    where doanhthu.giatri = (select max(doanhthu2.giatri)
    from (select sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.id) doanhthu2);
# Tìm họ tên khách hàng đã mua hóa đơn có trị giá cao nhất trong năm 2006.
    use demo20062;
    select name
    from (select `order`.id, sum(product.price * orderdetail.quantity) as giatri, customer.name
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    join customer on customer.id = `order`.customerId
    group by `order`.id) tong
    where tong.giatri = (select max(doanhthu2.giatri)
    from (select sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.id) doanhthu2);
# In ra danh sách 3 khách hàng (MAKH, HOTEN) mua nhiều hàng nhất (tính theo số lượng).
    use demo20062;
    select customer.id, customer.name, sum(orderdetail.quantity) as soluong
    from customer
    join `order` on customer.id = `order`.customerId
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by customer.id
    order by soluong desc
    limit 3;
# In ra danh sách các sản phẩm (MASP, TENSP) có giá bán bằng 1 trong 3 mức giá cao nhất.
    use demo20062;
    select product.id, product.name
    from product
    where product.price between 280 and 380;

# In ra danh sách các sản phẩm (MASP, TENSP) có tên bắt đầu bằng chữ M, có giá bằng 1 trong 3 mức giá cao nhất (của tất cả các sản phẩm).
    use demo20062;
    select product.id, product.name
    from product
    where product.price between 280 and 380
    and product.name like 'M%';
# Tính doanh thu bán hàng mỗi ngày.
            use demo20062;
    select `order`.time, sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.time;
# Tính tổng số lượng của từng sản phẩm bán ra trong tháng 10/2006.
    use demo20062;
    select product.name, sum(orderdetail.quantity)
    from product
    join orderdetail on product.id = orderdetail.productId
    join `order` on `order`.id = orderdetail.orderId
    where month(`order`.time) = 10
    and year(`order`.time) = 2006
    group by name;

# Tính doanh thu bán hàng của từng tháng trong năm 2006.
    use demo20062;
    select `order`.time, sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where year(`order`.time) = 2006
    group by month(`order`.time);
# Tìm hóa đơn có mua ít nhất 4 sản phẩm khác nhau.
    use demo20062;
    select `order`.id
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by `order`.id
    having count(product.id) >= 4;
# Tìm hóa đơn có mua 3 sản phẩm có giá <300 (3 sản phẩm khác nhau).
    use demo20062;
    select `order`.id,count(product.id)
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where product.price < 300
    group by `order`.id
    having count(product.id) = 3;
# Tìm khách hàng (MAKH, HOTEN) có số lần mua hàng nhiều nhất.
    use demo20062;
    select id,name,soluong
    from (select customer.id,customer.name,sum(orderdetail.quantity) as soluong
    from customer
    join `order` on customer.id = `order`.customerId
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by customer.id) soluongsp
    order by soluongsp.soluong desc
    limit 1;

# Tháng mấy trong năm 2006, doanh số bán hàng cao nhất?
    use demo20062;
    select time,giatri
    from (select `order`.time, sum(product.price * orderdetail.quantity) as giatri
    from `order`
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    where year(`order`.time) = 2006
    group by month(`order`.time) ) doanhthuthang
    order by doanhthuthang.giatri desc
    limit 1
    ;
# Tìm sản phẩm (MASP, TENSP) có tổng số lượng bán ra thấp nhất trong năm 2006.
    use demo20062;
    select product.id,product.name, sum(orderdetail.quantity) as soluongban
    from product
    join orderdetail on product.id = orderdetail.productId
    join `order` on `order`.id = orderdetail.orderId
    group by name
    order by soluongban
    limit 1;
# Trong 10 khách hàng có doanh số cao nhất, tìm khách hàng có số lần mua hàng nhiều nhất.
    use demo20062;
    select id,name,giatrimua,solanmua
    from (select customer.id,customer.name,sum(orderdetail.quantity*product.price) as giatrimua,count(productId) as solanmua
    from customer
    join `order` on customer.id = `order`.customerId
    join orderdetail on `order`.id = orderdetail.orderId
    join product on product.id = orderdetail.productId
    group by customer.id) soluongsp
    order by soluongsp.giatrimua desc
    limit 1;

}
