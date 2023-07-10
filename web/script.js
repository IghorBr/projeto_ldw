const api = {
    listTipoLancamentos : function (){
        return fetch(`http://localhost:8080/tipoLancamentos`, {
            method:"GET",
        })
    },
    list: function () {
        return fetch(`http://localhost:8080/lancamentos`, {
            method:"GET",
        })
    },
    get: function (id) {
        return fetch(`http://localhost:8080/lancamentos/${id}`, {
            method:"GET",
        })
    },
    delete: function (id) {
        return fetch(`http://localhost:8080/lancamentos/${id}`, {
            method:"DELETE",
        })
    },
    update: function (id, payload) {
        return fetch(`http://localhost:8080/lancamentos/${id}`, {
            method:"PUT",
            body: JSON.stringify(payload),
            headers: {
                "Content-Type": "application/json"
            }
        })
    },
    create: function (payload) {
        return fetch(`http://localhost:8080/lancamentos`, {
            method:"POST",
            body: JSON.stringify(payload),
            headers: {
                "Content-Type": "application/json"
            }
        })
    }
};

async function preencherLancamentos(){
    const tableBody = document.getElementById('lancamentos-table-body');
    const response = await api.list();
    const lancamentos = await response.json();

    lancamentos.forEach((lancamento) => {
        const linha = document.createElement("tr");

        const id = document.createElement("td");
        id.textContent = lancamento.id;
        linha.appendChild(id);

        const desc = document.createElement("td");
        desc.textContent = lancamento.descricao;
        linha.appendChild(desc);

        const tipo = document.createElement("td");
        tipo.textContent = lancamento.tipoLancamento;
        linha.appendChild(tipo);

        const valor = document.createElement("td");
        valor.textContent = lancamento.valor;
        linha.appendChild(valor);

        const criadoEm = document.createElement("td");
        criadoEm.textContent = lancamento.criadoEm;
        linha.appendChild(criadoEm);

        const acoes = document.createElement('td');
        const editButton = document.createElement('a');
        editButton.classList.add('btn', 'btn-warning', 'btn-edit', 'btn-sm');
        editButton.href = `/lancamento?id=${lancamento.id}`;
        const editIcon = document.createElement("i");
        editIcon.classList.add('bi', 'bi-pencil');
        editButton.appendChild(editIcon);
        acoes.appendChild(editButton);

        const deleteButton = document.createElement('button');
        deleteButton.classList.add('btn', 'btn-danger', 'btn-delete', 'btn-sm');
        deleteButton.setAttribute('data-bs-toggle', 'modal');
        deleteButton.setAttribute('data-bs-target', '#deleteModal')
        deleteButton.addEventListener('click',(event)=> {
            const btn = document.getElementById("btn-confirmar-delecao");
            btn.setAttribute('data-id', lancamento.id);
            btn.addEventListener('click', confirmarDelecao);
        });

        const deleteIcon = document.createElement("i");
        deleteIcon.classList.add('bi', 'bi-x');
        deleteButton.appendChild(deleteIcon);
        acoes.appendChild(deleteButton);

        linha.appendChild(acoes);
        tableBody.appendChild(linha);
    });
}

async function salvar(){
    const url = new URL(window.location.href);
    const id = url.searchParams.get('id');

    const lancamento = {
        descricao: document.getElementById("descricao").value,
        valor: document.getElementById("valor").value,
        tipoLancamento: document.getElementById("tipoLancamento").value,
        criadoEm: document.getElementById("criadoEm").value
    };

    const response = id ? await api.update(id ,lancamento) : await api.create(lancamento);
    if (response.status >= 200 && response.status < 300){
        location.href = "/";
    } else {
        console.log(response);
    }

}


async function confirmarDelecao(event){
    const id = event.target.getAttribute('data-id');
    const response = await api.delete(id);
    if (response.ok || response.status === 201){
        location.reload();
    }
}


async function preencherTipoLancamentos(){
    const response = await api.listTipoLancamentos();
    if (response.ok){
        const tiposLancamentos = await response.json();
        const tipoLancamentoSelect = document.getElementById("tipoLancamento");
        tiposLancamentos.forEach(tl => {
            const option = document.createElement("option");
            option.innerText = tl;
            tipoLancamentoSelect.appendChild(option);
        })
    }
}

async function preencherLancamento(){
    const url = new URL(window.location.href);
    const id = url.searchParams.get('id');

    const response = await api.get(id);
    if (response.ok){
        const lancamento = await response.json();
        document.getElementById("descricao").value = lancamento.descricao;
        document.getElementById("valor").value = lancamento.valor;
        document.getElementById("tipoLancamento").value = lancamento.tipoLancamento;
        document.getElementById("criadoEm").value = lancamento.criadoEm;

    }
}

function preencherDataAtual(){
    document.getElementById("criadoEm").value = new Date().toISOString().split('T')[0];
}