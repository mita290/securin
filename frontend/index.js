function clearTable() {
    const table = document.getElementById("main");
    while (table.rows.length > 1) {
        table.deleteRow(1);
    }
}

function render(data) {
    clearTable();
    let tab = document.getElementById("main");

    data.forEach(r => {
        let row = document.createElement("tr");
        row.innerHTML = `
            <td>${r.cuisine}</td>
            <td>${r.title}</td>
            <td>${r.description}</td>
            <td>${r.nutrients}</td>
            <td>${r.prepTime}</td>
            <td>${r.cookTime}</td>
            <td>${r.rating}</td>
            <td>${r.serves}</td>
            <td>${r.totalTime}</td>
        `;
        tab.appendChild(row);
    });
}

async function initial() {
    const res = await fetch("http://localhost:8080/api/recipes");
    const data = await res.json();
    render(data);
}

async function paged() {
    const limit = document.getElementById("limit").value;
    const offset = document.getElementById("offset").value;

    const res = await fetch(`http://localhost:8080/api/recipes/${limit}/${offset}`);
    const data = await res.json();
    render(data);
}

async function searchByTitle() {
    const title = document.getElementById("searchTitle").value;

    const res = await fetch(`http://localhost:8080/api/recipes/search/title?title=${title}`);
    const data = await res.json();
    render(data);
}

async function searchByCalories() {
    const cal = document.getElementById("searchCal").value + " kcal";

    const res = await fetch(`http://localhost:8080/api/recipes/search/calories?calories=${cal}`);
    const data = await res.json();
    render(data);
}

async function searchByTime() {
    const time = document.getElementById("searchTime").value;

    const res = await fetch(`http://localhost:8080/api/recipes/search/totaltime?time=${time}`);
    const data = await res.json();
    render(data);
}

async function searchByRating() {
    const rating = document.getElementById("searchRate").value;

    const res = await fetch(`http://localhost:8080/api/recipes/search/rating?rating=${rating}`);
    const data = await res.json();
    render(data);
}