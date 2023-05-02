import React, { useState } from "react"

import { getConditionsOp } from "../../Data/Api/Api"

import "./Condition.css"

function Condition(props) {
	const [conditionState, setConditionState] = props.condition

	const [publicationOptionsState, setPublicationOptionsState] = useState({
		conditions: [],
	})

	async function onClickSelectCondition() {
		if (!publicationOptionsState.conditions || publicationOptionsState.conditions.length <= 0) {
			const conditions = await getConditionsOp()
			setPublicationOptionsState({
				...publicationOptionsState,
				conditions: conditions,
			})
		}
	}

	const onChangeText = e => {
		setConditionState({
			...conditionState,
			[e.target.name]: e.target.value,
		})
	}

	return (
		<div className='input-container'>
			<div className='tag-container'>Condition:</div>
			<select
				name='condition'
				onClick={onClickSelectCondition}
				onChange={onChangeText}
			>
				<option
					selected
					disabled
				>
					Choose One
				</option>

				{publicationOptionsState.conditions.map(condition => {
					return (
						<option
							key={condition}
							name='condition'
							value={condition}
						>
							{condition}
						</option>
					)
				})}
			</select>
		</div>
	)
}

export { Condition }
