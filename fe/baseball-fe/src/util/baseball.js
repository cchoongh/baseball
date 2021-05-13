const Baseball = {
  pitch: () => {
    const rand =  Math.floor(Math.random() * 3);
    
    // if (rand === 0) return 'STRIKE';
    // if (rand === 1) return 'BALL';
    // if (rand === 2) return 'OUT';

    return 'BALL';
  },
  generateHitBase: () => {
    const rand = Math.random();
    return 1;

    console.log('hit random:', rand);
    if (rand >= 0.97) return 3;
    if (rand >= 0.90) return 2;
    return 1;
  },
  generateRecord: ({ action, gameState }) => ({
    player_id: gameState.batter.id,
    action,
    nth_batter: gameState.nth_batter,
    batter_name: gameState.batter.name,
    strike: gameState.ball_count.strike + (action === 'STRIKE' ? 1 : 0),
    ball: gameState.ball_count.ball + (action === 'BALL' ? 1 : 0)
  })
}

export default Baseball;