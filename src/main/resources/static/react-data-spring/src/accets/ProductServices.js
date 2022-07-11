import axios from "axios"; // npm install axios

const GET_REST_API_URL = 'http://localhost:22333/app/product/allPage?p=1';

class Items {

    getItems() {
        return axios.get(GET_REST_API_URL);
    };

    addItems() {
        return axios.post('http://localhost:22333/app/product/save');
    };

}

export default new Items();