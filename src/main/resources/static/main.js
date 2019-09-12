const addButton = document.querySelector(".addbutton");
var input = document.querySelector(".input");
const container = document.querySelector(".container");

var id_value = 0;
var baseurl = "http://localhost:8080/todo/";
function getTodo() {
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.open("GET", baseurl, true);
  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
      var todo = JSON.parse(xmlhttp.responseText);
      for (i = 0; i < todo.length; i++) {
        id_value = todo[i].id;
        var name = todo[i].name;
        getServerData(name);
      }
    }
  };
  xmlhttp.send();
}

function postTodo() {
  var newElement = input.value;
  var xmlhttp = new XMLHttpRequest(); // new HttpRequest instance
  xmlhttp.open("POST", baseurl, true);
  xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xmlhttp.send(
    JSON.stringify({ id: id_value, name: newElement, details: newElement })
  );
}

function deleteValueTodo(value) {
  var xhr = new XMLHttpRequest();
  xhr.open("DELETE", baseurl + value, true);
  xhr.onload = function() {
    var todoItem = JSON.parse(xhr.responseText);
    if (xhr.readyState == 4 && xhr.status == "200") {
      console.table(todoItem);
    } else {
      console.error(users);
    }
  };
  xhr.send(null);
}

window.onload = function() {
  this.getTodo();
};
class Item {
  constructor(itemName) {
    this.element = this.createDiv(itemName);
  }

  createDiv(itemName) {
    let input = document.createElement("input");
    input.value = itemName;
    var option = document.createElement("option");
    option.value = input.value;
    document.getElementById("list").appendChild(option);

    input.disabled = true;
    input.classList.add("item_input");
    input.type = "text";

    let itemBox = document.createElement("div");
    itemBox.classList.add("item");
    itemBox.id = id_value;

    let editButton = document.createElement("button");
    editButton.innerHTML = "EDIT";
    editButton.classList.add("editButton");

    let removeButton = document.createElement("button");
    removeButton.innerHTML = "REMOVE";
    removeButton.classList.add("removeButton");

    container.appendChild(itemBox);

    itemBox.appendChild(input);
    itemBox.appendChild(editButton);
    itemBox.appendChild(removeButton);

    editButton.addEventListener("click", () => this.edit(input));
    removeButton.addEventListener("click", () => this.remove(itemBox));
    return itemBox;
  }
  edit(input) {
    input.disabled = !input.disabled;
  }

  remove(Item) {
    var value = Item.id;
    console.log(value);
    deleteValueTodo(value);
    container.removeChild(Item);
  }
}

function check() {
  if (input.value != "") {
    ++id_value;
    new Item(input.value);
    console.log(input.value);

    postTodo();
    input.value = "";
  }
}
function getServerData(name) {
  new Item(name);
}

addButton.addEventListener("click", check);

window.addEventListener("keydown", e => {
  if (e.which == 13) {
    check();
  }
});
