function getDeleteTemplateForSyncExample(value, row) {
    return `
        <button onclick="postDeleteKpac(${row.id})" class="delete_icon fas fa-trash"></button>
    `;
}

//Async function alternative located below
function postDeleteKpac(kpacId) {
    document.getElementById("kpacId").value = kpacId;
    document.getElementById("deleteForm").submit();
}

const grid = new dhx.Grid("kpacGrid", {
    columns: [
        { id: "id", header: [{ text: "ID" }, { content: "inputFilter" }], type: "number", numberMask: { allowNegative: false, maxIntLength: 19 }},
        { id: "title", header: [{ text: "Title" }, { content: "inputFilter" }], type: "string" },
        { id: "description", header: [{ text: "Description" }, { content: "inputFilter" }], type: "string" },
        { id: "creationDate", header: [{ text: "Creation date" }, { content: "inputFilter" }], type: "string",  },
        { id: "delete", header: "Delete", template: getDeleteTemplateForSyncExample /*getDeleteTemplate*/, htmlEnable: true }
    ],
    height: "auto",
    autoWidth: true,
    data: KPAC_DATA,
    editable: false,
    sortable: true,
    tooltip: false,
    eventHandlers: {
        onclick: {
            delete_icon: function(event, data) {
                deleteKpac(data?.row?.id);
            },
        }
    }
});


//Async alternative of form submission

/*document.getElementById("kpacForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;

    fetch('/kpacs/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, description })
    }).then(response => response.json())
        .then(data => {
            grid.data.add({
                id: data.id,
                title: data.title,
                description: data.description,
                creationDate: data.creationDate,
                delete: getDeleteTemplate()
            });

            document.getElementById("kpacForm").reset();
        })
        .catch(error => console.error('[kpac creation] Error:', error));
});*/



//Async alternative of pack deletion

/*function deleteKpac(rowId) {
    if(rowId) {
        fetch('/kpacs/delete?' + new URLSearchParams({
            id: rowId
        }).toString(), {
            method: 'DELETE'
        }).then(response => {
            if (response.status === 200) {
                grid.data.remove(rowId);
            } else {
                console.error('[kpac deletion] response error', error);
            }
        })
            .catch(error => {
                console.error('[kpac deletion] integration error', error);
            });
    } else {
        console.error('[kpac deletion] rowId undefined');
    }
}*/