const _MS_PER_DAY = 1000 * 60 * 60 * 24;

function calcDateDiff(date1, date2) {
  const utc1 = Date.UTC(date1.year, date1.month, date1.day);
  const utc2 = Date.UTC(date2.year, date2.month, date2.day);

  return Math.floor((utc2 - utc1) / _MS_PER_DAY);
}

export default calcDateDiff;