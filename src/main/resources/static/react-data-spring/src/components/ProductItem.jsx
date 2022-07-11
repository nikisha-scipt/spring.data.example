import React, {useEffect, useState} from "react";
import ProductServices from "../accets/ProductServices";
import Pagination from "./Pagination";
import axios from "axios";
import styles from "./module/cart.module.scss";
import {Link} from "react-router-dom";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";



function productItems() {
    const [items, setItems] = useState([]);
    const [pageCount, setPageCount] = useState(1);
    const [basket, setBasket] = useState([]);


    useEffect(() => {
        axios.get("http://localhost:22333/app/product/Cart")
            .then(res => {
                setBasket(res.data);
            });
    }, []);

    useEffect(() => {
        getItems();
    }, []);

    const getItems = () => {
        ProductServices.getItems().then(resp => {
            setItems(resp.data);
            console.log(resp);
        })
    };

    useEffect(() => {
        fetch('http://localhost:22333/app/product/allPage?p=' + pageCount + '&s=' + 5)
            .then((res) => res.json())
            .then((arr) => {
                setItems(arr);
            })
    }, [pageCount])

    const deleteRow = (id, e) => {
        axios.delete(`http://localhost:22333/app/product/delete/${id}`)
            .then(res => {
                console.log(res);
                console.log(res.data);
                window.location.reload();
            });
    }

    function addItemToBasket(title, i) {
        axios.get(`http://localhost:22333/app/product/addToCart/${title}`)
            .then(res => {
                console.log(res);
                console.log(res.data);
                setBasket(res.data);
            })
    };




    const [formValue, setFormValue] = useState({
        title: '',
        price: ''
    });

    const handleSubmit = async() => {
        const titleFormData = new FormData();
        titleFormData.append("title", formValue.title)
        titleFormData.append("price", formValue.price)

        try {
            const response = await axios({
                method: "post",
                url: "http://localhost:22333/app/product/save",
                data: titleFormData,
                headers: { "Content-Type": "multipart/form-data" },
            });
        } catch(error) {
            console.log(error)
        }
        window.location.reload();
        history.replaceState("object or string", "title", "/another-new-url");
    }

    const handleChange = (event) => {
        setFormValue({
            ...formValue,
            [event.target.name]: event.target.value
        });
    }


    return (
        <div className="product_item">
            <table key={Math.random()} className="table">
                <thead>
                <tr>
                    <td>id</td>
                    <td>Title</td>
                    <td>Price</td>
                    <td>Info</td>
                    <td>Basket</td>
                </tr>
                </thead>
                <tbody>
                {
                    items.map((item, i) => [
                        <tr key={Math.random()}>
                            <td key={item.id}>{item.id}</td>
                            <td key={item.title}>{item.title}</td>
                            <td key={item.price}>{item.price}</td>
                            <td>
                                <button className="btn"  onClick={(e) => deleteRow(item.id, e)}>удалить</button>
                            </td>
                            <td>
                                <button key={i} className="btn" onClick={(i) => addItemToBasket(item.title, i)} >добавить</button>
                            </td>
                        </tr>
                    ])
                }
                </tbody>
            </table>

            <div className = "input__table">
                <Form onSubmit = { handleSubmit } >
                    <Form.Group className="mb-3">
                        <input type = "text" name = "title" placeholder="title" value={formValue.title} onChange= {handleChange}/>
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <input type = "text" name = "price" placeholder="price" value={formValue.price} onChange= {handleChange}/>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Сохранить
                    </Button>
                </Form>
            </div>

            <Pagination onChangePage={(number) => setPageCount(number)}/>
            <Link to="/basket">
                <div className={styles.root}>
                    <i>{basket.length}</i>
                </div>
            </Link>
        </div>

    )
}

export default productItems;