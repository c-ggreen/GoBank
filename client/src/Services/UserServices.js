import axios from 'axios'

const URL = "http://localhost:8080/v1/user/"

class UserService{
    getAllUsers = () =>{
        return axios.get(URL)
    }
    getUserByEmail = (email) =>{
        return axios.get(URL + 'email/' + email)
    }
    postUser = (user) =>{
        return axios.post(URL, user)
    }
    patchUser = (user) =>{
        return axios.patch(URL, user)
    }
    deleteUser = (email) =>{
        return axios.delete(URL+email)
    }
}

export default new UserService()