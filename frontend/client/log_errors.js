async function log_error(error) {
    console.error('Error occurred:', error.message);
    if (process.env.NODE_ENV === 'development') {
        console.error(error.stack);
    }
}
module.exports = {log_error}