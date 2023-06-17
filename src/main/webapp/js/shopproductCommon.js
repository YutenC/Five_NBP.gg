
let host_context = '';
let nowDate;
const currentDate = new Date();
const year = currentDate.getFullYear();
const month = String(currentDate.getMonth() + 1).padStart(2, '0');
const day = String(currentDate.getDate()).padStart(2, '0');
nowDate = `${year}-${month}-${day}`;

gethost_context();



function gethost_context() {
    let pathName = window.document.location.pathname;
    let locathost = window.document.location.origin;
    // pathName = '/';
    if ('/' === pathName) {
        host_context = locathost + "/";
    }
    else {
        let contextpath = pathName.split('/')[1];
        host_context = locathost + "/" + contextpath + "/";
    }

    host_context = "http://localhost:8080/shop/";
}

function getDataFromSessionStorage() {
    e.preventDefault();
    let todo_list;
    // 取得資料
    for (let index = 0; index < sessionStorage.length; index++) {
        const key = sessionStorage.key(index);
        const value = sessionStorage.getItem(key);
        if (key === "todo_list") {
            todo_list = sessionStorage.getItem(key);
        }
    }

    const obj = JSON.parse(decodeURIComponent(todo_list));
    return obj;
}

function saveDataToSessionStorage(list) {
    const jsonData = JSON.stringify(list)
    sessionStorage.setItem('todo_list', encodeURIComponent(jsonData));
}


export { host_context, nowDate };