import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Books from '../Books/books';
import Categories from '../Categories/categories';
import Authors from '../Authors/authors';
import Header from '../Header/header';
import ProductAdd from '../Books/ProductAdd/productAdd';
import EShopService from "../../repository/eshopRepository";
import ProductEdit from "../Books/ProductEdit/productEdit";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            categories: [],
            selectedProduct: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/authors"} exact render={() =>
                            <Authors authors={this.state.authors}/>}/>
                        <Route path={"/categories"} exact render={() =>
                            <Categories categories={this.state.categories}/>}/>
                        <Route path={"/products/add"} exact render={() =>
                            <ProductAdd categories={this.state.categories}
                                        authors={this.state.authors}
                                        onAddProduct={this.addProduct}/>}/>
                        <Route path={"/products/edit/:id"} exact render={() =>
                            <ProductEdit categories={this.state.categories}
                                         authors={this.state.authors}
                                         onEditProduct={this.editProduct}
                                         books={this.state.selectedProduct}/>}/>
                        <Route path={"/products"} exact render={() =>
                            <Books books={this.state.books}
                                      onDelete={this.deleteProduct}
                                      onEdit={this.getProduct}/>}/>
                        <Redirect to={"/products"}/>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadAuthors();
        this.loadCategories();
        this.loadProducts();
    }

    loadAuthors = () => {
        EShopService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadProducts = () => {
        EShopService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadCategories = () => {
        EShopService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    deleteProduct = (id) => {
        EShopService.deleteProduct(id)
            .then(() => {
                this.loadProducts();
            });
    }

    addProduct = (id,name, category, author, copies) => {
        EShopService.addProduct(id,name, category, author, copies)
            .then(() => {
                this.loadProducts();
            });
    }

    getProduct = (id) => {
        EShopService.getProduct(id)
            .then((data) => {
                this.setState({
                    selectedProduct: data.data
                })
            })
    }

    editProduct = (id, name, price, quantity, category, manufacturer) => {
        EShopService.editProduct(id, name, price, quantity, category, manufacturer)
            .then(() => {
                this.loadProducts();
            });
    }
}

export default App;
