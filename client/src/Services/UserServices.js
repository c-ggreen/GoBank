import axios from 'axios'

const URL = "http://localhost:8080/user/"

class UserService{
    getAllUsers = () =>{
        return axios.get(URL)
    }
    getUserById = (email) =>{
        return axios.get(URL + email)
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