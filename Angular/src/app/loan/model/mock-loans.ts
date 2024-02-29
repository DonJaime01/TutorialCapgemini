import { LoanPage } from "./LoanPage";

export const LOAN_DATA: LoanPage = {
    content: [
        { id: 1, game: { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' } }, client: { id: 1, name: 'Juan' }, initDate: new Date(2020,11,9), endDate: new Date(2021,12,11) },
        { id: 2, game: { id: 2, title: 'Juego 2', age: 22, category: { id: 2, name: 'Categoría 2' }, author: { id: 2, name: 'Autor 2', nationality: 'Nacionalidad 2' } }, client: { id: 2, name: 'Alberto' }, initDate: new Date(2020,11,12), endDate: new Date(2021,11,17) },
        { id: 3, game: { id: 3, title: 'Juego 3', age: 12, category: { id: 3, name: 'Categoría 3' }, author: { id: 3, name: 'Autor 3', nationality: 'Nacionalidad 3' } }, client: { id: 3, name: 'Berto' }, initDate: new Date(2023,1,10), endDate: new Date(2023,4,28) },
        { id: 4, game: { id: 4, title: 'Juego 4', age: 11, category: { id: 4, name: 'Categoría 4' }, author: { id: 4, name: 'Autor 4', nationality: 'Nacionalidad 4' } }, client: { id: 4, name: 'Andres' }, initDate: new Date(2022,10,5), endDate: new Date(2023,2,1) },
    ],  
    pageable : {
        pageSize: 5,
        pageNumber: 0,
        sort: [
            {property: "id", direction: "ASC"}
        ]
    },
    totalElements: 4
}