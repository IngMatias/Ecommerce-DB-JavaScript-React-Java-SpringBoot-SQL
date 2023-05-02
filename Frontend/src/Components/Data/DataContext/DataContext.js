import React, { createContext, useState } from "react"

const DataContext = createContext()

const DataProvider = ({ children }) => {
	const [state, setState] = useState({})

	return (
		<DataContext.Provider
			value={{
				state,
				setState,
			}}
		>
			{children}
		</DataContext.Provider>
	)
}
export { DataContext, DataProvider }
