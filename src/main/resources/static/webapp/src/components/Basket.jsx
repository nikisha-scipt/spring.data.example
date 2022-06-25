import {useEffect, useState} from "react";
import axios from "axios";

const Basket = () => {

    const [items, setItems] = useState([]);

    function getItemsAtBasket() {
        axios.get('http://localhost:22333/app/product/Cart')
                .then(res => {
                    setItems(res.data);
                });
            }
            useEffect(() => {
            getItemsAtBasket();
            }, []);

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
                            <tr key={i}>
                                <td key={item.id}>{item.id}</td>
                                <td key={item.title}>{item.title}</td>
                                <td key={item.price}>{item.price}</td>
                            </tr>
                        ])
                    }
                    </tbody>
                </table>

            </div>
            );
};

export default Basket;
