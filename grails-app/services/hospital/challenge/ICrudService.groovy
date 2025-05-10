package hospital.challenge

interface ICrudService<T> {

    def findALl(params)

    def save(T entity)

    def update(T entity, Long id)

    def delete(T entity)
}