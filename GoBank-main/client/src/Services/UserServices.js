import axios from "axios";

const URL = "http://localhost:8080/v1/user/";

class UserService {
  getAllUsers = () => {
    return axios.get(URL);
  };
  getUserById = (id) => {
    return axios.get(`${URL}${id}`);
  };
  getUserByEmail = (email) => {
    return axios.get(`${URL}e/${email}`);
  };
  postUser = (user) => {
    return axios.post(URL, user);
  };
  patchUser = (id) => {
    return axios.patch(`${URL}/update/${id}`);
  };
  deleteUser = (id) => {
    return axios.delete(`${URL}${id}`);
  };
}

export default new UserService();
