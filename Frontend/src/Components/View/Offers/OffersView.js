import React, { useContext } from "react"

import { DataContext } from "../../Data/DataContext/DataContext"
import { acceptOfferOp } from "../../Data/Api/Api"

import "./OffersView.css"

function OffersView(props) {
	const offers = props.offers

	const { state, setState } = useContext(DataContext)

	const acceptOffer = title => {
		acceptOfferOp(state.user, { title }, { offerNewState: "Accepted" })
			.then(() => console.log("Accepted"))
			.catch(e => setErrorMsg(e.error))
	}
	const setErrorMsg = errorMsg => {
		document.getElementById("error-msg").innerHTML = errorMsg
	}

	return (
		<div>
			{offers.map((offer, index) => {
				return (
					<div
						key={index}
						className='offer-container'
					>
						<p>
							<b>Offertor </b>
							{offer.email}
						</p>
						<p>
							<b>Title </b>
							{offer.title}
						</p>
						<p>
							<b>Description </b>
							{offer.description}
						</p>
						<p>
							<b>State </b>
							{offer.state}
						</p>
						<p>
							<b>Date </b>
							{offer.date}
						</p>

						{offer.state != "Accepted" && state.user ? (
							<button onClick={() => acceptOffer(offer.title)}>Accept</button>
						) : (
							<div></div>
						)}

						<div id='error-msg'></div>
					</div>
				)
			})}
		</div>
	)
}

export { OffersView }
