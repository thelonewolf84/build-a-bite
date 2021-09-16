window.addEventListener("load", function() {

const row = document.querySelector('.row');
const addMoreFields = document.getElementById('add-more-fields');
const removeFields = document.getElementById('remove-fields');
const ingredientContainer = document.querySelector('.ingredient-container');
const ingredientType = document.getElementById('ingredientType1');
const form = document.querySelector('form');

let inputCount = 2;

let options = ingredientType.children;

addMoreFields.onclick = function() {

    if (ingredientContainer.children.length <= 10) {
        const newIngredient = document.createElement('input');
        const newMeasurement = document.createElement('input');
        const rowDiv = document.createElement('div');
        const ingredientFormGroup = document.createElement('div');
        const ingredientTypeFormGroup = document.createElement('div');
        const measurementFormGroup = document.createElement('div');
        const ingredientLabel = document.createElement('label');
        const ingredientTypeLabel = document.createElement('label');
        const measurementLabel = document.createElement('label');


        rowDiv.setAttribute('class', 'row');
        ingredientFormGroup.setAttribute('class', 'form-group col-4');
        measurementFormGroup.setAttribute('class', 'form-group col-4');
        ingredientTypeFormGroup.setAttribute('class', 'form-group col-4');

        ingredientLabel.setAttribute('for', 'ingredientName' + inputCount);

        newIngredient.setAttribute('type', 'text');
        newIngredient.setAttribute('id', 'ingredientName' + inputCount);
        newIngredient.setAttribute('name', 'ingredientName' + inputCount);
        newIngredient.setAttribute('class', 'form-control');
        newIngredient.setAttribute('placeholder', 'Ingredient Name');

        ingredientTypeLabel.setAttribute('for', 'ingredientType' + inputCount);

        const select = document.createElement('select');
        select.setAttribute('class', 'form-control');
        select.setAttribute('name', 'ingredientType' + inputCount);
        select.setAttribute('id', 'ingredientType' + inputCount);

        for (let i = 0; i < ingredientType.children.length; i++) {
            let option = document.createElement("option");
            option.value = ingredientType.children[i].value;
            option.text = ingredientType.children[i].text;
            select.appendChild(option);
        }

        measurementLabel.setAttribute('for', 'measurement' + inputCount)

        newMeasurement.setAttribute('type', 'text');
        newMeasurement.setAttribute('id', 'measurement' + inputCount);
        newMeasurement.setAttribute('name', 'measurement' + inputCount);
        newMeasurement.setAttribute('class', 'form-control');
        newMeasurement.setAttribute('placeholder', 'Measurement ex 1 cup');

        ingredientContainer.appendChild(rowDiv);

        ingredientFormGroup.appendChild(ingredientLabel);
        ingredientFormGroup.appendChild(newIngredient);

        ingredientTypeFormGroup.appendChild(ingredientTypeLabel);
        ingredientTypeFormGroup.append(select);

        measurementFormGroup.appendChild(measurementLabel);
        measurementFormGroup.appendChild(newMeasurement);

        rowDiv.appendChild(ingredientFormGroup);
        rowDiv.appendChild(ingredientTypeFormGroup);
        rowDiv.appendChild(measurementFormGroup);

        inputCount++;
    }
}

removeFields.onclick = function() {
    let removeItem = ingredientContainer.lastElementChild;

    if (ingredientContainer.children.length > 1) {
        removeItem.remove();
        inputCount--;
    }
}
});