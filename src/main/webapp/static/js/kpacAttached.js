const grid = new dhx.Grid("kpacAttachedGrid", {
    columns: [
        { id: "id", header: [{ text: "ID" }, { content: "inputFilter" }], type: "number", numberMask: { allowNegative: false, maxIntLength: 20 }},
        { id: "title", header: [{ text: "Title" }, { content: "inputFilter" }], type: "string" },
        { id: "description", header: [{ text: "Description" }, { content: "inputFilter" }], type: "string" },
    ],
    height: "auto",
    autoWidth: true,
    data: KPAC_ATTACHED_DATA,
    editable: false,
    sortable: true,
    tooltip: false
});