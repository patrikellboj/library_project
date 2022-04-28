const categoryApp = {
  baseURL: 'http://localhost:8080',
  init: () => {
    document.addEventListener('DOMContentLoaded', categoryApp.getCategories);
    document.querySelector('#addNewCategory').addEventListener('click', () => {
      categoryApp.addNewCategory();
    })
  },
  getCategories: () => {
    fetch(categoryApp.baseURL + '/categories', {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then(resp => resp.json())
      .then(categories => {
        // Chaning fetch to get deletableCategories after categories
        fetch(categoryApp.baseURL + '/categories/deletable', {
          method: 'GET',
          mode: 'cors',
          headers: {
            'Content-Type': 'application/json',
          },
        })
          .then(response => response.json()
          .then(deletableCategories => categoryApp.displayCategories(categories, deletableCategories)));
      })
      .catch(err => console.log(err));
  },
  displayCategories: (data, deletableCategories) => {
    let categories = document.querySelector('#categories');

    data.forEach(category => {
      // See if current category.id is in deletableCategories or not.
      // If false, set delete button to 'disabled'
      let setDisabled = '';
      setDisabled = deletableCategories.includes(category.id) ? '' : 'disabled';
  
      categories.innerHTML +=`
        <tr>
          <td>${category.id}</td>
          <td>${category.categoryName}</td>
          <td>
            <button type="button" class="btn btn-link" data-toggle="collapse" data-target="#collapseAddOrEditCategory" aria-expanded="false" aria-controls="collapseAddOrEditCategory" onclick="categoryApp.editCategory('${category.id}', '${category.categoryName}')">Edit</button>
            <button ${setDisabled} type="button" class="btn btn-link" onclick="category.deleteCategory(${category.id})">Delete</button>
          </td>
        </tr>
        `
    });
  },
  deleteCategory: (id) => {
    fetch(categoryApp.baseURL + '/categories/delete/' + id, {
      method: 'DELETE',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(response => console.log(response.json()))
    .catch(err => console.log(err));
    window.location.reload();
  },
  editCategory: (id, exsistingName) => {

    let categoryNameLabel = document.querySelector('#categoryNameLabel')
    categoryNameLabel.innerHTML = `Category Name for ID: ${id}`

    let categoryNameValidation = document.querySelector('#categoryNameValidation');
    categoryNameValidation.value = exsistingName;

    let newName;

    document.querySelector('#saveCategory').addEventListener('click', (e) => {
      // e.preventDefault();
      newName = categoryNameValidation.value;

      fetch(categoryApp.baseURL + '/categories/edit/' + id, {
        method: 'PUT',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          id: id,
          categoryName: newName
        })
      })
      .then(response => console.log(response.json()))
      .catch(err => console.log(err));
      // window.location.reload();
    });
  },
  addNewCategory: () => {
    console.log('in add new category');
    let categoryNameLabel = document.querySelector('#categoryNameLabel')
    categoryNameLabel.innerHTML = `Add new Category`;

    let categoryNameValidation = document.querySelector('#categoryNameValidation');

    document.querySelector('#saveCategory').addEventListener('click', (e) => {
      e.preventDefault();
      console.log('clicked save button from addNewCategory');

      let newCategory = categoryNameValidation.value;

      fetch(categoryApp.baseURL + '/categories', {
        method: 'POST',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          categoryName: newCategory
        })
      })
      .then(response => console.log(response.json()))
      .catch(err => console.log(err));
      window.location.reload();
    });
  }
}
categoryApp.init();