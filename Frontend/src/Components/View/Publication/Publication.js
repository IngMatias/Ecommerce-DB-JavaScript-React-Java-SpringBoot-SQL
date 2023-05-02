import React from "react"

import { Link } from "react-router-dom"

import "./Publication.css"

function Publication(props) {
	return (
		<Link
			to={"/publication/" + props.publication.title}
			className='publication-container'
		>
			<div>
				<div>
					<img
						className='publication-img'
						src={props.publication.photo}
						alt=""
					></img>
				</div>
				<div className='publication-info'>
					<div>
						<b>Title</b> {props.publication.title}
					</div>
					<div>
						<b>Description</b> {props.publication.description}
					</div>
					<div>
						<b>Date</b> {props.publication.date}
					</div>
				</div>
			</div>
		</Link>
	)
}

export { Publication }
