var Url = "http://localhost:8080/api/";
function getapi() {
    fetch('http://localhost:8080/api/getAllDish')
      .then(response => response.json())
      .then(data => handleData(data["data"]))
      .catch(error => console.error(error));

    fetch('http://localhost:8080/api/category/getAll')
      .then(response => response.json())
      .then(data => handelCategory(data["data"]))
      .catch(error => console.error(error));
      
  }
getapi();
function handleData(data){
  console.log(data)
    document.getElementById('list-item-product').innerHTML = "";
    // Lặp qua từng phần tử trong danh sách món ăn
    data.forEach(dish => {
     var item = `
     <div class="product-list col-xs-12">
     <div class="product-item">
         <div class="item-overlay">
             <div class="clickable">
                 <a href="#">${dish.name}</a>
             </div>
         </div>
         <div class="image">
             <a href="#"><img src="http://localhost:8080/static/image/${dish.image}" alt="Product 1"></a>
         </div>
         <div class="caption">
             <div class=" ">
                 <a href="#">${dish.name}</a>
             </div>
             <div class="description">
                 <p></p>
             </div>
             <div class="rating">
                 <span class="fa fa-stack"><i class="fa fa-star"></i></span>
                 <span class="fa fa-stack"><i class="fa fa-star"></i></span>
                 <span class="fa fa-stack"><i class="fa fa-star"></i></span>
                 <span class="fa fa-stack"><i class="fa fa-star"></i></span>
                 <span class="fa fa-stack"><i class="fa fa-star-o"></i></span>
             </div>
             <div class="price">
                 <span>$${dish.price}</span>
             </div>
             <div class="cart">
                 <button type="button" class="btn btn-primary">Add to Cart</button>
             </div>
         </div>
         <button type="button" class="btn btn-default wishlist" data-toggle="tooltip" data-placement="right" title="Wishlist"><i class="fa fa-heart"></i></button>
         <button type="button" class="btn btn-default compare" data-toggle="tooltip" data-placement="right" title="Compare"><i class="fa fa-circle-o"></i></button>
     </div>
 </div>`;

      document.querySelector('#list-item-product').innerHTML += item;
    });
}
function handelCategory(data){
  // Lặp qua từng phần tử trong danh sách danh mục
  console.log(data);
  document.querySelector('.list-group').innerHTML+= '';
    data.forEach(category => {
      // Tạo một phần tử HTML mới cho mỗi món ăn
      let cate = `<a href="#" onclick="getDishByCate(${category.id})" class="list-group-item">${category.name}</a>`;
      document.querySelector('.list-group').innerHTML+= cate;
      })
      // Thêm phần tử mới vào trang web
}

function getDishByCate(id){
  fetch("http://localhost:8080/api/dish/findByCategoryId/"+id)
  .then(response => response.json())
  .then(data =>  handleData(data["data"]))
  .catch(error => console.error(error));
}

//
function showBill(){
  document.querySelector(".bill").id = 'bill-active';
}
function hideBill(){
  document.querySelector('.bill').id = '';
}