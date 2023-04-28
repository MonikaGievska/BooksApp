import React from 'react';
import {useHistory} from 'react-router-dom';

const ProductEdit = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "Book1",
        category: 0,
        author: 0,
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.product.name;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.product.availableCopies;
        const category = formData.category !== 0 ? formData.category : props.product.category.id;
        const author = formData.author !== 0 ? formData.author : props.product.author.id;

        props.onEditProduct(props.product.id, name, availableCopies, category, author);
        history.push("/products");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.product.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="copies"
                               name="copies"
                               placeholder={props.product.availableCopies}
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) => {
                                if(props.product.category !== undefined &&
                                    props.product.category.id === term.id)
                                    return <option selected={props.product.category.id} value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if(props.product.author !== undefined &&
                                    props.product.author.id === term.id)
                                    return <option selected={props.product.author.id} value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default ProductEdit;