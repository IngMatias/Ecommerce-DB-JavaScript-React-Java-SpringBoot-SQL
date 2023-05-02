import React, { useState } from "react"
import { Sticker } from "../Sticker/Sticker"

function Stickers(props) {
	const { stickers, setStickers } = props.stickers

	const addSticker = () => {
		setStickers([
			...stickers,
			{
				country: "",
				number: "",
				photo: "",
			},
		])
	}

	const delSticker = e => {
		const index = e.target.dataset.index
		const newStickers = JSON.parse(JSON.stringify(stickers))
		if (index > -1) {
			newStickers.splice(index, 1)
		}
		setStickers(newStickers)
	}

	const setSticker = (index, prop, value) => {
		const newStickers = JSON.parse(JSON.stringify(stickers))
		console.log(index, prop, value)
		newStickers[index][prop] = value
		setStickers(newStickers)
	}

	const getSticker = index => {
		return JSON.parse(JSON.stringify(stickers[index]))
	}

	return (
		<div>
			<h1>Sticker</h1>
			{stickers.map((sticker, index) => {
				return (
					<div key={index}>
						<Sticker
							data-index={index}
							sticker={{ getSticker, setSticker }}
						/>
						<div
							data-index={index}
							onClick={delSticker}
						>
							Remove
						</div>
					</div>
				)
			})}
			<div>
				<button onClick={addSticker}>Add Sticker</button>
			</div>
		</div>
	)
}
export { Stickers }
