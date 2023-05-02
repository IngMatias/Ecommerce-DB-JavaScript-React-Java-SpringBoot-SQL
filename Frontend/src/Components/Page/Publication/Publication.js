import { useContext, useEffect, useState } from "react"
import { useParams } from "react-router-dom"

import { DataContext } from "../../Data/DataContext/DataContext"

import { Offer } from "../../Input/Offer/Offer"

import { OffersView } from "../../View/Offers/OffersView"

import { getPublicationOp, createOfferOp } from "../../Data/Api/Api"

function Publication() {
	const { state, setState } = useContext(DataContext)
	const { title } = useParams()

	const [publicationState, setPublicationState] = useState({})

	const [offerState, setOfferState] = useState({})

	const [stickers, setStickers] = useState([])

	useEffect(() => {
		getPublicationOp({ title })
            .then(pub => {
                setPublicationState(pub)
                console.log(JSON.stringify(pub))
            })
			.catch(() => console.log("ERROR"))
	}, [])

	const createOffer = () => {
		setOfferState({
			...offerState,
			stickers: stickers,
		})
		createOfferOp(state.user, publicationState.publication, offerState)
	}

	if (publicationState.publication) {
		return (
			<div className="container vertical-flex">
				<div className="publication-info">
					<p><b>Title </b>{publicationState.publication.title}</p>
					<p><b>Description </b>{publicationState.publication.description}</p>
					<p><b>Condition </b>{publicationState.publication.condition}</p>
					<p><b>Date </b>{publicationState.publication.date}</p>
					<p><b>Email </b>{publicationState.publication.email}</p>
					<img src={publicationState.publication.photo} />
				</div>
				<div>
					<OffersView offers={publicationState.offers} />
				</div>
				<div>
					<Offer
						offer={{ offerState, setOfferState }}
						sticker={{ stickers, setStickers }}
					/>
				</div>
				<button onClick={createOffer}>Send Offer</button>
			</div>
		)
	}
}

export { Publication }
