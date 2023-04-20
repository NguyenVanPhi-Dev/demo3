var Url = "http://localhost:8080/api/";
function getapi() {
    fetch('http://localhost:8080/api/getAllDish')
      .then(response => response.json())
      .then(data => handleData(data))
      .catch(error => console.error(error));

    fetch('http://localhost:8080/api/category/getAll')
      .then(response => response.json())
      .then(data => handelCategory(data))
      .catch(error => console.error(error));
      
  }
getapi();
function handleData(data){
  console.log(data)
    document.getElementById('dish-list').innerHTML = "";
    // Lặp qua từng phần tử trong danh sách món ăn
    data.forEach(dish => {
      // Tạo một phần tử HTML mới cho mỗi món ăn
      const card = document.createElement('div');
      card.className = 'card';
      card.style.width = '18rem';
      card.style.display = 'inline-block';

      const img = document.createElement('img');
      img.className = 'card-img-top';
      img.src = "http://localhost:8080/static/image/"+dish.image;
      img.alt = 'Card image cap';
      img.style.height = '12rem';

      const cardBody = document.createElement('div');
      cardBody.className = 'card-body';

      const title = document.createElement('h5');
      title.className = 'card-title';
      title.textContent = dish.name;

      const text = document.createElement('p');
      text.className = 'card-text';
      text.textContent = 'Hello' ;

      const link = document.createElement('a');
      link.className = 'btn btn-primary';
      link.href = '#';
      link.textContent = 'Buy';

      cardBody.appendChild(title);
      cardBody.appendChild(text);
      cardBody.appendChild(price);
      cardBody.appendChild(link);

      card.appendChild(img);
      card.appendChild(cardBody);

      // Thêm phần tử mới vào trang web
      document.getElementById('dish-list').appendChild(card);
    });
}
function handelCategory(data){
  // Lặp qua từng phần tử trong danh sách danh mục
    data.forEach(category => {
      // Tạo một phần tử HTML mới cho mỗi món ăn
      const navbar = document.createElement('nav');
      navbar.className = 'navbar navbar-light bg-light';

      const a = document.createElement('div');
      a.className = 'navbar-brand';
      a.textContent = category.name;
      a.setAttribute("data-id",category.id);

      a.addEventListener('click', function(){
        var id =  this.getAttribute("data-id");
        getDishByCate(id)
        console.log(id);
      });

      navbar.appendChild(a);
      document.getElementById('menu-list').appendChild(navbar);
      })
      // Thêm phần tử mới vào trang web
  
}

function getDishByCate(id){
  fetch("http://localhost:8080/api/dish/findByCategoryId/"+id)
  .then(response => response.json())
  .then(data =>  handleData(data))
  .catch(error => console.error(error));
}
