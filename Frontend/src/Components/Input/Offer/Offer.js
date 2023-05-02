import React, { useState } from "react"
import { Stickers } from "../Stickers/Stickers"

function Offer(props) {
	const { offerState, setOfferState } = props.offer

    const {stickers, setStickers } = props.sticker

	const onChangeText = e => {
		setOfferState({
			...offerState,
			[e.target.name]: e.target.value,
		})
	}

	return (
		<div>
			<h1>Offer</h1>
			<div>
				<input
					name='title'
					type='text'
					placeholder='title'
					onChange={onChangeText}
				></input>
				<input
					name='description'
					type='text'
					placeholder='description'
					onChange={onChangeText}
				></input>
			</div>
			<Stickers stickers={{ stickers, setStickers }} />
		</div>
	)
}

export { Offer }
