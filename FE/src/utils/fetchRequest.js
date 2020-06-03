const fetchRequest = (url, method) => {
  return fetch(url, {
    method: method,
    mode: "cors",
    cache: "no-cache",
    headers: {
      "Content-Type": "application/json",
    },
  });
};

export default fetchRequest;