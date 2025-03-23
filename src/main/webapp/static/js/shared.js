const deleteTemplate = `<span class="delete_icon fas fa-trash"></span>`;

const getDeleteTemplate = (row, value) => {
    if (!value || row?.$group) return "";
    return deleteTemplate;
}