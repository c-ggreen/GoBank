import axios from 'axios'

const URL = "http://localhost:8080/account/"

class AccountService{
    getAllAccounts = () =>{
        return axios.get(URL)
    }
    getAccountById = (id) =>{
        return axios.get(URL + id)
    }
    postAccount = (account) =>{
        return axios.post(URL, account)
    }
    patchAccount = (account) =>{
        return axios.patch(URL, account)
    }
    deleteAccount = (id) =>{
        return axios.delete(URL+id)
    }
}

export default new AccountService()