import styles from "./module/pag.module.scss";
import ReactPaginate from "react-paginate";

const pagination = ({onChangePage}) => {
    return (
        <ReactPaginate
                       className= {styles.root}
                       nextLabel=">"
                       onPageChange={(event) => {
                           onChangePage(event.selected + 1)
                       }}
                       pageRangeDisplayed={10}
                       pageCount={3}
                       previousLabel="<"
                       renderOnZeroPageCount={null}/>
    );
};

export default pagination;