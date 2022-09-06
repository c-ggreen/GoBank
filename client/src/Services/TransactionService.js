import axios from 'axios'

const URL = "http://localhost:8080/transaction/"

class TransactionService{
    getAllTransactions = () =>{
        return axios.get(URL)
    }
    getTransactionById = (id) =>{
        return axios.get(URL + id)
    }
    postTransaction = (Transaction) =>{
        return axios.post(URL, Transaction)
    }
    patchTransaction = (Transaction) =>{
        return axios.patch(URL, Transaction)
    }
    deleteTransaction = (id) =>{
        return axios.delete(URL+id)
    }
}

export default new TransactionService()