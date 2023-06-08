let manager_array = [];

// 使用 AJAX 發送請求獲取 managerList 的資料

fetch('../manager/manager_list', {
    method: 'GET',
})
    .then(response => response.json())
    .then(data => {
        // 獲取到 managerList 的資料後，動態生成 HTML 內容
        let managerListContainer = document.querySelector('table#manager_list tbody');

        // console.log(managerListContainer);

        let html = '';


        // console.log(data.managerList);

        // 根據 managerList 的資料生成 HTML
        data.managerList.forEach(manager => {

            let manager_array_item = {
                manager_id: manager.manager_id,
                account: manager.account,
                password: manager.password,
                name: manager.name,
                email: manager.email,
                phone: manager.phone,
                address: manager.address,
                is_working: manager.is_working
            }
            manager_array[manager.manager_id] = (manager_array_item);

            console.log(manager_array_item);
            // console.log(manager_array);



            html += `
        <tr>
          <td>${manager.manager_id}</td>
          <td>${manager.account}</td>
          <td class="manager-password">${manager.password}</td>
          <td>${manager.name}</td>
          <td>${manager.email}</td>
          <td> ${manager.phone}</td>
          <td>${manager.is_working}</td>
          <td>
              <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                  role="button" href="#"
                  onclick="ShowAllInfoClick(${manager.manager_id})">
                  詳細資料
              </a>
              <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                  role="button" href="#"
                  onclick="ChangeStateClick(${manager.manager_id})">
                  調整在職狀態
              </a>
              <br style="padding-top: 3px; padding-buttom: 3px;">
              <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button"
                  role="button" href="manager_edit.html"
                  onclick="EditClick(${manager.manager_id})">
                  修改資料
              </a>
              <a class="btn btn-primary btn-sm d-none d-sm-inline-block custom-manager-button btn-danger"
                  role="button" href="#"
                  onclick="RemoveClick(${manager.manager_id})">
                  刪除管理員
              </a>
          </td>
        </tr>
      `;
        });

        // 插入生成的 HTML 內容到 managerListContainer 元素中
        managerListContainer.innerHTML = html;
    })
    .catch(error => {
        console.error('Error:', error);
    });

function ShowAllInfoClick(id) {
    console.log(id);
    alert(manager_array[id].address);
}

//WIP
function ChangeStateClick(id) {

    // 使用 AJAX 發送請求，將 ID 值傳送到後端
    fetch('/your/backend/endpoint', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id })
    })
        .then(response => response.json())
        .then(data => {
            // 在此處處理後端回傳的資料

        })
        .catch(error => {
            console.error('Error:', error);
        });

}

function EditClick(id) {

    // 將 ID 儲存到 sessionStorage 中
    sessionStorage.setItem('id', id);

    // // 使用 AJAX 發送請求，將 ID 值傳送到後端
    // fetch('../manager/getManagerEditInfo', {
    //     method: 'POST',
    //     headers: { 'Content-Type': 'application/json' },
    //     body: JSON.stringify({ id: id })
    // })
    //     .then(response => {
    //         // 將 ID 值儲存到 sessionStorage 中
    //         sessionStorage.setItem('id', id);
    //         return response.json();
    //     })
    //     .then(data => {
    //         // 在此處處理後端回傳的資料
    //         console.log(data);
    //     })
    //     .catch(error => {
    //         console.error('Error:', error);
    //     });

}

function RemoveClick(id) {

    // 使用 AJAX 發送請求，將 ID 值傳送到後端
    fetch('/your/backend/endpoint', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id })
    })
        .then(response => response.json())
        .then(data => {
            // 在此處處理後端回傳的資料
        })
        .catch(error => {
            console.error('Error:', error);
        });

}