const libraryItemsApp = {
  baseURL: 'http://localhost:8080',
  init: () => {
    document.addEventListener('DOMContentLoaded', libraryItemsApp.getLibraryItems);
  },
  getLibraryItems: () => {
    fetch(libraryItemsApp.baseURL + '/items', {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(resp => resp.json())
    .then(libraryItems => {
      libraryItemsApp.displayLibraryItems(libraryItems);
    })
    .catch(err => console.log(err));
  },
  displayLibraryItems: (data) => {
    let libraryItems = document.querySelector('#libraryItems');

    data.forEach(item => {
      libraryItems.innerHTML +=`
        <tr>
          <td>${item.id}</td>
          <td>${item.category.categoryName}</td>
          <td>${item.type}</td>
          <td>${item.title}</td>
          <td>${item.borrowable}</td>
          <td>${item.borrower}</td>
          <td>${item.borrowDate}</td>
          <td>${item.author}</td>
          <td>${item.pages}</td>
          <td>${item.runTimeMinutes}</td>
          <td>
            <button type="button" class="btn btn-link" onclick="libraryItemsApp.editLibraryItem(${item.id})">Edit</button>
            <button type="button" class="btn btn-link" onclick="libraryItemsApp.deleteLibraryItem(${item.id})">Delete</button>
          </td>
        </tr>
        `
      });
  },
  deleteLibraryItem: (id) => {
    fetch(libraryItemsApp.baseURL + '/items/delete/' + id, {
      method: 'DELETE',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(response => console.log(response.json()))
    .catch(err => console.log(err));
  
    window.location.reload();
  }
}
libraryItemsApp.init();