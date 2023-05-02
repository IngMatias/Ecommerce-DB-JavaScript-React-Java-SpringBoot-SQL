import React, { useState } from "react"

import { getCountriesOp, getNumbersByCountryOp } from "../../Data/Api/Api"

function Sticker(props) {
	const { getSticker, setSticker } = props.sticker

	let onChangePhotoFunction = undefined
	if (props.sticker.onChangePhoto) {
		onChangePhotoFunction = props.sticker.onChangePhoto
	}

	const index = props["data-index"]

	const [stickerOptionsState, setStickerOptionsState] = useState({
		countries: [],
		numbers: [],
	})

	async function onClickSelectCountry() {
		if (!stickerOptionsState.countries || stickerOptionsState.countries.length <= 0) {
			const countries = await getCountriesOp()
			setStickerOptionsState({
				...stickerOptionsState,
				countries: countries,
			})
		}
	}

	async function onClickSelectNumber() {
		const numbers = await getNumbersByCountryOp(getSticker(index).country)
		setStickerOptionsState({
			...stickerOptionsState,
			numbers: numbers,
		})
	}

	const onChange = e => {
		if (e.target.name == "country") {
			const select = e.currentTarget.parentNode.querySelectorAll("select[name='number']")[0]
			select.options[0].selected = true
			setSticker(index, "number", "")
		}
		setSticker(index, e.target.name, e.target.value)
	}

	const onChangePhoto = e => {
		const file = e.target.files[0]
		const reader = new FileReader()
		reader.onload = event => {
			setSticker(index, "photo", event.target.result)
		}
		reader.readAsDataURL(file)
		if (onChangePhotoFunction) {
			onChangePhotoFunction(e)
		}
		
	}

	return (
		<div>
			<div>
				<select
					id='country'
					name='country'
					onClick={onClickSelectCountry}
					onChange={onChange}
				>
					<option
						selected
						disabled
					>
						Choose One
					</option>
					{stickerOptionsState.countries.map(country => {
						return (
							<option
								key={country}
								name='country'
								value={country}
							>
								{country}
							</option>
						)
					})}
				</select>
				<select
					id='number'
					name='number'
					onClick={onClickSelectNumber}
					onChange={onChange}
				>
					<option
						selected
						disabled
					>
						Choose One
					</option>
					{stickerOptionsState.numbers.map(number => {
						return (
							<option
								key={number}
								name='number'
								value={number}
							>
								{number}
							</option>
						)
					})}
				</select>
			</div>
			<div>
				<input
					name='photo'
					type='file'
					onChange={onChangePhoto}
				></input>
			</div>
		</div>
	)
}

export { Sticker }
