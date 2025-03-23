const grid = new dhx.Grid("kpacSetGrid", {
    columns: [
        { id: "id", header: [{ text: "ID" }, { content: "inputFilter" }], type: "number", numberMask: { allowNegative: false, maxIntLength: 19 }},
        { id: "title",  header: [{ text: "Title" }, { content: "inputFilter" }], type: "string" },
        { id: "delete", header: "Delete", template: getDeleteTemplate, htmlEnable: true }
    ],
    height: "auto",
    autoWidth: true,
    data: KPAC_SET_DATA,
    editable: false,
    sortable: true,
    tooltip: false,
    eventHandlers: {
        onclick: {
            delete_icon: function(event, data) {
                deleteKpacSet(data?.row?.id);
            },
        },
    }
});

function deleteKpacSet(rowId) {
    if(rowId) {
        fetch('/sets/delete?' + new URLSearchParams({
            id: rowId
        }).toString(), {
            method: 'DELETE'
        }).then(response => {
            if (response.status === 200) {
                grid.data.remove(rowId);
            } else {
                console.error('[kpac-set deletion] response error', error);
            }
        }).catch(error => {
            console.error('[kpac-set deletion] integration error', error);
        });
    } else {
        console.error('[kpac-set deletion] rowId undefined');
    }
}

document.getElementById("kpacSetForm").addEventListener("submit", function(event) {
    event.preventDefault();
    let title = document.getElementById("title").value;
    let selectedKpacs = [...document.querySelectorAll("#kpacList input:checked")].map(cb => cb.id.split("_")[1]);

    fetch('/sets/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: title, kpacs: selectedKpacs })
    })
        .then(response => response.json())
        .then(data => {
            grid.data.add({
                id: data.id,
                title: data.title,
                delete: getDeleteTemplate()
            });
            document.getElementById("kpacSetForm").reset();
        }).catch(error => console.log("[kpac-set] Integration error : " + error));
});

grid.events.on("cellDblClick", function(row, column, event) {
    window.open('/set/' + row.id, "_blank");
});