import React from 'react';
import "./Transaction.css"

function Transaction({id, description, amount}) {
    return (
        <div className='transactionContainer'>
            <p>{`ID#: ${id}`}</p>
            <p>{description}</p>
            <p>{`$${amount}`}</p>
        </div>
    );
}

export default Transaction;