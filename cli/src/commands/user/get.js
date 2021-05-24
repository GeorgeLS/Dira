'use strict';
const { Command, flags } = require('@oclif/command');
const { DiraUserClient } = require("dira-clients");


class GetUserCommand extends Command {
    async run() {
        const client = new DiraUserClient();
        const { flags } = this.parse(GetUserCommand);
        if (flags.all) {
            const users = await client.get_all_users();
            for (var user of users) {
                this.log(user);
            }
        } else if (flags.id) {
            const user = await client.get_user_by_id(flags.id);
            this.log(user);
        }
    }
}

GetUserCommand.description = `Retrieve user information
...
`;

GetUserCommand.flags = {
    all: flags.boolean({ char: 'a', description: 'Get all users', default: true }),
    id: flags.integer({ description: 'Get user by id' }),
};

module.exports = GetUserCommand;